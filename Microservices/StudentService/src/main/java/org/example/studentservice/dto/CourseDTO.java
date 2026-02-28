package org.example.studentservice.dto;

public class CourseDTO {

    private Long courseId;
    private String courseName;
    private String facultyName;
    private String duration;

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getFacultyName() { return facultyName; }
    public void setFacultyName(String facultyName) { this.facultyName = facultyName; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
}