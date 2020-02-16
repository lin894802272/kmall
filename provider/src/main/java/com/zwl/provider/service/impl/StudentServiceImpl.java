package com.zwl.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kmall.pojo.Student;
import com.kmall.service.StudentService;
import com.zwl.provider.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2019/9/30 0030.
 */
@Service(timeout =3000,version = "1.0.0", interfaceClass = StudentService.class)   //dubbo service注解，需要指定版本,并告述它要实现那个接口,然后消费者消费的时候，需要指定版本号
@org.springframework.stereotype.Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public List<Student> findAll() {
        return studentMapper.selectList(null);
    }
}
