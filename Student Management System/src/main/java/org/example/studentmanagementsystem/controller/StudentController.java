package org.example.studentmanagementsystem.controller;

import org.example.studentmanagementsystem.entity.Student;
import org.example.studentmanagementsystem.repository.StudentRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }


    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return repository.save(student);
    }


    @GetMapping
    public List<Student> getAllStudents() {
        return repository.findAll();
    }


    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }


    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        Student existing = repository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(student.getName());
            existing.setAge(student.getAge());
            return repository.save(existing);
        }
        return null;
    }


    @PatchMapping("/{id}")
    public Student patchStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        Student existing = repository.findById(id).orElse(null);
        if (existing != null) {
            if (student.getName() != null) {
                existing.setName(student.getName());
            }
            if (student.getAge() != 0) {
                existing.setAge(student.getAge());
            }
            return repository.save(existing);
        }
        return null;
    }


    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        repository.deleteById(id);
        return "Student deleted successfully";
    }
}

