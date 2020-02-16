package com.zwl.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2019/10/1 0001.
 */
@RestController
public class portalController {

    @RequestMapping("demo")
    public ModelAndView demo(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

}
