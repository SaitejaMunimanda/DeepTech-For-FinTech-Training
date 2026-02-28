package org.example.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.studentservice.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
