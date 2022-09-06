package com.example.demo.RoleSercurity;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.RoleSercurity.SpringSecurityAuthorities.*;

public enum SpringSecurityRoles {

    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(STUDENT_READ,STUDENT_WRITE,ADMIN_READ,ADMIN_WRITE)),
    ADMIN_TRAINEE(Sets.newHashSet(STUDENT_READ,ADMIN_READ));

   private final Set<SpringSecurityAuthorities> roles;



    SpringSecurityRoles(Set<SpringSecurityAuthorities> roles) {
        this.roles = roles;
    }

    public Set<SpringSecurityAuthorities> getRoles() {
        return roles;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){

        Set<SimpleGrantedAuthority> simpleGrantedSet = getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getSpringSecurityAuthorities()))
                .collect(Collectors.toSet());

        simpleGrantedSet.add(new SimpleGrantedAuthority("ROLE_"+this.name()));

        return simpleGrantedSet;

    }


}
