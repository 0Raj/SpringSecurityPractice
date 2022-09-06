package com.example.demo.RoleSercurity;

public enum SpringSecurityAuthorities {
    STUDENT_READ("student_read"),
    STUDENT_WRITE("student_write"),
    ADMIN_READ("admin_read"),
    ADMIN_WRITE("admin_write");

    private final String SpringSecurityAuthorities;

    SpringSecurityAuthorities(String roles_string) {

        this.SpringSecurityAuthorities = roles_string;
    }

    public String getSpringSecurityAuthorities() {
        return SpringSecurityAuthorities;
    }
}
