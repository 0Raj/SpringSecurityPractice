package com.example.demo.RoleSercurity;

public enum Roles {
    STUDENT_READ("student_read"),
    STUDENT_WRITE("student_write"),
    ADMIN_READ("admin_read"),
    ADMIN_WRITE("admin_write");

    private final String roles_string;

    Roles(String roles_string) {
        this.roles_string = roles_string;
    }
}
