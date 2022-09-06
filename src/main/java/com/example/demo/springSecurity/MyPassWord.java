package com.example.demo.springSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MyPassWord {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

}
