package com.zwl.item.service.impl;

import javax.annotation.Resource;

import com.kmall.redis.dao.JedisDao;
import com.kmall.service.TbItemDescDubboService;
import com.zwl.item.service.TbItemDescService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;


@Service
public class TbItemDescServiceImpl implements TbItemDescService {
	@Reference(version = "1.0.0")
	private TbItemDescDubboService tbItemDescDubboServiceImpl;
	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${redis.desc.key}")
	private String descKey;
	@Override
	public String showDesc(long itemId) {
		String key = descKey + itemId;
		if(jedisDaoImpl.exists(key)){
			String json = jedisDaoImpl.get(key);
			if(json!=null&&!json.equals("")){
				return json;
			}
		}
		String result = tbItemDescDubboServiceImpl.selByItemid(itemId).getItemDesc();
		jedisDaoImpl.set(key, result);
		return result;
	}

}
