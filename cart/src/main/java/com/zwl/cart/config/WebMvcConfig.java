package com.zwl.cart.config;


import com.zwl.cart.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Administrator on 2019/10/8 0008.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    public void addInterceptors(InterceptorRegistry registry){
        //拦截所有的请求
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");

    }

}