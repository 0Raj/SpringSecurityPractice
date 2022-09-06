package com.example.demo.springSecurity;

import com.example.demo.RoleSercurity.Roles;
import com.example.demo.RoleSercurity.SpringSecurityRoles;
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

import static com.example.demo.RoleSercurity.SpringSecurityRoles.*;


@Configuration
@EnableWebSecurity
public class MySpringConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MySpringConfig(PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;
    }

//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//
//
//        return http.build();
//
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/student/**")
                .hasRole(ADMIN.toString())
                .antMatchers("index","/css/*","/js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        UserDetails rajUserDetail = User
                .builder()
                .username("Raj")
                .password(passwordEncoder.encode("Raj@4317"))
                .roles(ADMIN.toString())
                .build();
        UserDetails studentUserDetail = User
                .builder()
                .username("Gowtham")
                .password(passwordEncoder.encode("2381"))
                .roles(STUDENT.toString())
                .build();

        return new InMemoryUserDetailsManager(rajUserDetail,studentUserDetail);
    }
}
