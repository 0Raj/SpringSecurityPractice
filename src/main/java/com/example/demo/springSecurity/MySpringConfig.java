package com.example.demo.springSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class MySpringConfig {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MySpringConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        return http.build();

    }


    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        UserDetails rajUserDetail = User
                .builder()
                .username("Raj")
                .password(passwordEncoder.encode("Raj@4317"))
                .roles("Admin")
                .build();

        return new InMemoryUserDetailsManager(rajUserDetail);
    }
}
