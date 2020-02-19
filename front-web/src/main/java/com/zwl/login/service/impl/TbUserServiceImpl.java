package com.zwl.login.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kmall.pojo.TbUser;
import com.kmall.redis.dao.JedisDao;
import com.kmall.service.TbUserDubboService;
import com.zwl.commons.pojo.EgoResult;
import com.zwl.commons.utils.CookieUtils;
import com.zwl.commons.utils.JsonUtils;
import com.zwl.login.service.TbUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;


@Service
@Slf4j
public class TbUserServiceImpl implements TbUserService {
    @Reference(version = "1.0.0")
    private TbUserDubboService tbUserDubboServiceImpl;
    @Resource
    private JedisDao jedisDaoImpl;

    @Override
    public EgoResult login(TbUser user, HttpServletRequest request, HttpServletResponse response) {
        EgoResult er = new EgoResult();
        TbUser userSelect = tbUserDubboServiceImpl.selByUser(user);
        if (userSelect != null) {
            er.setStatus(200);
            //当用户登录成功后把用户信息放入到redis中
            String key = UUID.randomUUID().toString();
            jedisDaoImpl.set(key, JsonUtils.objectToJson(userSelect));
            jedisDaoImpl.expire(key, 60 * 60 * 24 * 7);
            //产生Cookie
            CookieUtils.setCookie(request, response, "TT_TOKEN", key, 60 * 60 * 24 * 7);
        } else {
            er.setMsg("用户名和密码错误");
        }
        return er;
    }

    @Override
    public EgoResult getUserInfoByToken(String token) {
        EgoResult er = new EgoResult();
        String json = jedisDaoImpl.get(token);
        if (json != null && !json.equals("")) {
            TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
            //可以把密码清空
            tbUser.setPassword(null);
            er.setStatus(200);
            er.setMsg("OK");
            er.setData(tbUser);
        } else {
            er.setMsg("获取失败");
        }

        return er;
    }

    @Override
    public EgoResult logout(String token, HttpServletRequest request, HttpServletResponse response) {
        Long index = jedisDaoImpl.del(token);
        CookieUtils.deleteCookie(request, response, "TT_TOKEN");
        EgoResult er = new EgoResult();
        er.setStatus(200);
        er.setMsg("OK");
        return er;
    }

    @Override
    public EgoResult registry(TbUser tbUser) {
        try {
            Date date = new Date();
            tbUser.setCreated(date);
            tbUser.setUpdated(date);
            int registry = tbUserDubboServiceImpl.registry(tbUser);
            if (registry > 0) {
                EgoResult egoResult = new EgoResult();
                egoResult.setStatus(200);
                egoResult.setMsg("OK");
                egoResult.setData(registry);
                return egoResult;

            }
        } catch (Exception e) {
            log.error("新增用户发生异常 tbUser = {}", tbUser, e);
            EgoResult egoResult = new EgoResult();
            egoResult.setStatus(500);
            egoResult.setMsg("error");
            egoResult.setData(0);
            return egoResult;
        }

        EgoResult egoResult = new EgoResult();
        egoResult.setStatus(500);
        egoResult.setMsg("新增失败");
        egoResult.setData(0);
        return egoResult;
    }


}
