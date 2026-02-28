package org.example.courseservice.service;


import org.example.courseservice.entity.Course;
import org.example.courseservice.exception.ResourceNotFoundException;
import org.example.courseservice.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Course create(Course course) {
        return repository.save(course);
    }

    public List<Course> getAll() {
        return repository.findAll();
    }

    public Course getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Course update(Long id, Course course) {
        Course existing = getById(id);
        existing.setCourseName(course.getCourseName());
        existing.setFacultyName(course.getFacultyName());
        existing.setDuration(course.getDuration());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}