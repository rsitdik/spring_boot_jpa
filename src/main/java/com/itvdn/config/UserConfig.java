package com.itvdn.config;

import com.itvdn.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    User friend1() {
        return new User("Oleg");
    }

    @Bean
    User friend2() {
        return new User("Luiza");
    }

    @Bean
    User friend3() {
        return new User("Gesha");
    }

    @Bean
    User friend4() {
        return new User("Mistral");
    }

}
