package com.kmall.controller;

import com.kmall.redis.dao.JedisDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Created by Administrator on 2019/10/5 0005.
 */
@RestController
public class TesstController {

    @Resource
    private JedisDao jedisDaoImpl;




    @RequestMapping("aa" )
    public String demo(){
       return   jedisDaoImpl.get("aa");
    }

}
