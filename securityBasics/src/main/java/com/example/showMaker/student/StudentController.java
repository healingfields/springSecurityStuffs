package com.example.showMaker.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private static final List<Student> students = Arrays.asList(
            new Student(1,"bones"),
            new Student(2,"michael kiwanuka"),
            new Student(3,"sopico")
    );

    @GetMapping("{studentId}")
    public Student  getStudent(@PathVariable("studentId") Integer studentId){
        return students.stream()
                .filter(student -> student.getId().equals(studentId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(" student with " + studentId + " not found "));
    }

    @GetMapping
    public List<Student> getStudents(){
        return students;
    }

}
