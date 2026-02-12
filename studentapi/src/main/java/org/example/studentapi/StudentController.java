package org.example.studentapi;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    List<Student> students = new ArrayList<>();

    @PostMapping
    public String addStudent(@RequestBody Student student) {
        students.add(student);
        return "Student added successfully";
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }


    @PutMapping("/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setName(updatedStudent.getName());
                s.setAge(updatedStudent.getAge());
                return "Student updated completely";
            }
        }
        return "Student not found";
    }


    @PatchMapping("/{id}")
    public String updateStudentPartially(
            @PathVariable int id,
            @RequestBody Student updatedStudent) {

        for (Student s : students) {
            if (s.getId() == id) {

                // update only name if present
                if (updatedStudent.getName() != null) {
                    s.setName(updatedStudent.getName());
                }

                // update only age if present
                if (updatedStudent.getAge() != 0) {
                    s.setAge(updatedStudent.getAge());
                }

                return "Student updated partially";
            }
        }
        return "Student not found";
    }


    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        students.removeIf(s -> s.getId() == id);
        return "Student deleted";
    }
}

