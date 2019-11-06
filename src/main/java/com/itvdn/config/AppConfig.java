package com.itvdn.config;

import com.itvdn.aspects.MyLogger;
import com.itvdn.model.Authorization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public MyLogger myLogger() {
        return new MyLogger();
    }

    @Bean
    Authorization authorization() {
        return new Authorization();
    }
}
