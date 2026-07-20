package com.example.studentapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentapi.entity.Student;
import com.example.studentapi.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
public class StudentController {
 private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

     @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @RequestBody Student studentDetails) {

        Student student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            return null;
        }

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setCourse(studentDetails.getCourse());

        return studentRepository.save(student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentRepository.deleteById(id);

        return "Student deleted successfully";
    }
}

