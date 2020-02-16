package com.kmall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2019/9/30 0030.
 */
@Data    //lombok的使用
@TableName(value = "tab_student")    //mybatis-plus
public class Student implements Serializable {   //记得实现序列化


    @TableId(value="sid",type = IdType.AUTO)
    private Integer sid;
    private String sname;
    private String sex;
    private Date birthday;
    private String phone;
    private String address;
    private Integer cid;



}
