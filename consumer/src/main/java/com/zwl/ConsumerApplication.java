package com.zwl;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//关于启动后面为什么增加后面那个语句的解释：因为SpringBoot如果配置了mybatis的话，就需要去找寻数据库，所以我们需要告知服务器，不需要自动配置
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDubboConfiguration  //启动dubbo服务
@ComponentScan(basePackages = {"com.zwl","com.kmall"})
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}
