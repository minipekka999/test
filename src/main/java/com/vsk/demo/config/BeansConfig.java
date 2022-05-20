package com.vsk.demo.config;

import com.vsk.demo.beans.FirstBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public FirstBean firstBean(){
        return new FirstBean();
    }

    @Bean
    public  FirstBean secondBean(){
        return new FirstBean("Ilyas", 11);
    }
}
