package org.example.studentservice.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.example.studentservice.entity.Student;
import org.example.studentservice.service.StudentService;
import org.example.studentservice.dto.*;
import org.example.studentservice.feign.CourseClient;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;
    private final CourseClient courseClient;

    public StudentController(StudentService service, CourseClient courseClient) {
        this.service = service;
        this.courseClient = courseClient;
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return service.create(student);
    }

    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/details")
    public StudentDetails getStudentWithCourse(@PathVariable Long id) {

        Student student = service.getById(id);

        CourseDTO course = courseClient.getCourseById(student.getCourseId());

        StudentDetails response = new StudentDetails();
        response.setStudentId(student.getStudentId());
        response.setStudentName(student.getStudentName());
        response.setEmail(student.getEmail());
        response.setCourse(course);

        return response;
    }
    @GetMapping("/test-course/{id}")
    public CourseDTO testCourse(@PathVariable Long id) {
        return courseClient.getCourseById(id);
    }
}