package com.example.demo.RoleSercurity;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

import static com.example.demo.RoleSercurity.Roles.*;

public enum SpringSecurityRoles {

    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(STUDENT_READ,STUDENT_WRITE,ADMIN_READ,ADMIN_WRITE));

   private final Set<Roles> roles;



    SpringSecurityRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Set<Roles> getRoles() {
        return roles;
    }


}
