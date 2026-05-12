package com.example.SpringBootApp.controller;

import com.example.SpringBootApp.model.Grade;
import com.example.SpringBootApp.repository.GradeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeRepository gradeRepository;

    public GradeController(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @GetMapping
    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }

    @PostMapping
    public Grade add(@RequestBody Grade grade) {
        return gradeRepository.save(grade);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        gradeRepository.deleteById(id);
    }
}