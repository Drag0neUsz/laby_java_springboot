package com.example.SpringBootApp.controller;

import com.example.SpringBootApp.model.Course;
import com.example.SpringBootApp.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @PostMapping
    public Course add(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @GetMapping("/ects/{value}")
    public List<Course> getByEcts(@PathVariable int value) {
        return courseRepository.findByEcts(value);
    }
}