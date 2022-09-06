package com.example.demo.springSecurity;

import com.example.demo.RoleSercurity.SpringSecurityAuthorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


import static com.example.demo.RoleSercurity.SpringSecurityRoles.*;
import static com.example.demo.RoleSercurity.SpringSecurityAuthorities.*;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers("/api/student/**").hasRole(ADMIN.toString())
//                .antMatchers(HttpMethod.GET,"/admin/api/student/**").hasAnyRole(ADMIN.toString(),ADMIN_TRAINEE.toString())
//                .antMatchers(HttpMethod.PUT,"/admin/api/student/**").hasAuthority(ADMIN_WRITE.getSpringSecurityAuthorities())
//                .antMatchers(HttpMethod.POST,"/admin/api/student/**").hasAuthority(ADMIN_WRITE.getSpringSecurityAuthorities())
//                .antMatchers(HttpMethod.DELETE,"/admin/api/student/**").hasAuthority(ADMIN_WRITE.getSpringSecurityAuthorities())
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
//                .roles(ADMIN.toString())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        UserDetails studentUserDetail = User
                .builder()
                .username("Gowtham")
                .password(passwordEncoder.encode("2381"))
//                .roles(STUDENT.toString())
                .authorities(STUDENT.getGrantedAuthorities())
                .build();
        UserDetails adminTraineeUserDetail = User
                .builder()
                .username("Dhana")
                .password(passwordEncoder.encode("void"))
//                .roles(ADMIN_TRAINEE.toString())
                .authorities(ADMIN_TRAINEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(rajUserDetail,studentUserDetail,adminTraineeUserDetail);
    }
}
