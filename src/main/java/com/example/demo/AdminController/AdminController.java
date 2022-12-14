package com.example.demo.AdminController;

import com.example.demo.studentController.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin/api/student")
public class AdminController {
    private List<Student> studentList = Arrays.asList(new Student(1,"Raj"),
            new Student(2,"Gowtham"),
            new Student(3,"Dhana") );

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_TRAINEE')")
    public List<Student> getAllStudent(){
        return this.getStudentList();
    }

    @PostMapping("addStudent")
    @PreAuthorize("hasAuthority('admin_write')")
    public void registerNewStudent(@RequestBody Student student){
        //this.getStudentList().add(student);
        System.out.println(student);
        System.out.println("registerNewStudent");

    }
    @DeleteMapping(path = "deleteStudent/{studentId}")
    @PreAuthorize("hasAuthority('admin_write')")
    public String registerNewStudent(@PathVariable("studentId") Integer studentId ){
        this.getStudentList().remove(studentId);
        return "Deleted successfully";
    }
    @PutMapping(path = "updateStudent/{studentId}")
    @PreAuthorize("hasAuthority('admin_write')")
    public String updateStudentRecord(@PathVariable("studentId") Integer studentId ,@RequestBody Student student){
        Student resultStudent =  this.getStudentList().get(studentId);
        resultStudent.setStudentId(student.getStudentId());
        resultStudent.setName(student.getName());

        return "udated successfully";
    }

    public List<Student> getStudentList() {
        return studentList;
    }


}
