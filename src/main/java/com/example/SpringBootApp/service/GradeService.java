package com.example.SpringBootApp.service;

import com.example.SpringBootApp.model.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> findByStudentId(Integer studentId);
}
