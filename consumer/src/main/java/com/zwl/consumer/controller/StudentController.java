package com.zwl.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kmall.pojo.Student;
import com.kmall.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2019/9/30 0030.
 */
@Controller
public class StudentController {


   // @Reference(version = "1.0.0")   //dubbo的注解，需要指定使用的Service的版本，不然无法找到对应的版本
   // private StudentService studentService;



  /*  @GetMapping("all")
    public List<Student> demo(){
        return studentService.findAll();
    }


    @RequestMapping("index")
    public String page(){
        return "index";
    }*/

    @RequestMapping("all1")
    public String demo(){
        return "index";
    }

}