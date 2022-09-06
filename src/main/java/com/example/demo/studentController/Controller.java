package com.example.demo.studentController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/student")
public class Controller {

    private static List<Student> studentList = Arrays.asList(new Student(1,"Raj"),
            new Student(2,"Gowtham"),new Student(3,"Dhana") );

    @GetMapping(path="{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        return studentList.stream()
                .filter(s-> studentId.equals(s.getStudentId()))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Bad Request"));
    }
}
