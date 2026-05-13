package com.example.SpringBootApp.service;

import com.example.SpringBootApp.exception.*;
import com.example.SpringBootApp.model.Grade;
import com.example.SpringBootApp.model.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudent(Integer id) throws StudentNotFoundException;
    Student addStudent(Student student) throws StudentInvalidAgeException, InvalidNameException;
    Student updateStudent(Integer id, Student student) throws StudentNotFoundException;
    boolean deleteStudent(Integer id) throws StudentNotFoundException;
    int countStudents();
    //biznesówki
    Double getAverage(Integer id) throws StudentNotFoundException;
    List<Grade> getStudentGrades(Integer id) throws StudentNotFoundException;
    List<Student> getTopStudents() throws StudentNotFoundException;
}