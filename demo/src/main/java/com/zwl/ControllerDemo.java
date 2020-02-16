package com.zwl;






import com.kmall.redis.dao.JedisDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2019/10/5 0005.
 */
@RestController
public class ControllerDemo {


    @Resource
    private JedisDao jedisDaoImpl;

    @Value("${search.url}")
    private String url ;


    @Value("${redis.item.key}")
    private String itemKey;



    @RequestMapping("bb")
    public String demo(){
        return jedisDaoImpl.get("aa");
    }



}
