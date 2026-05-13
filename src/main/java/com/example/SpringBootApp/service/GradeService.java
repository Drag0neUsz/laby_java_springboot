package com.example.SpringBootApp.service;

import com.example.SpringBootApp.exception.CourseNotFoundException;
import com.example.SpringBootApp.exception.GradeInvalidGradeException;
import com.example.SpringBootApp.exception.GradeNotFoundException;
import com.example.SpringBootApp.exception.StudentNotFoundException;
import com.example.SpringBootApp.model.Grade;
import com.example.SpringBootApp.model.Student;

import java.util.List;

public interface GradeService {
    List<Grade> getAllGrades();
    List<Grade> findByStudentId(Integer id);
    Grade getGrade(Integer id) throws GradeNotFoundException;
    Grade addGrade(Grade grade) throws GradeInvalidGradeException, CourseNotFoundException, StudentNotFoundException;
    Grade updateGrade(Integer id, Grade gradeDetails) throws GradeNotFoundException, GradeInvalidGradeException;
    boolean deleteGrade(Integer id) throws GradeNotFoundException;
}
