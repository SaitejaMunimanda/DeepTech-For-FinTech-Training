package org.example.studentservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.example.studentservice.dto.CourseDTO;

@FeignClient(name = "COURSESERVICE")
public interface CourseClient {

    @GetMapping("/courses/{id}")
    CourseDTO getCourseById(@PathVariable Long id);
}