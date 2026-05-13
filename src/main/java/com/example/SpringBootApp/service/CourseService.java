package com.example.SpringBootApp.service;

import com.example.SpringBootApp.exception.*;
import com.example.SpringBootApp.model.Course;
import com.example.SpringBootApp.model.Grade;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course getCourseById(Integer id);
    Course addCourse(Course course) throws InvalidNameException, CourseInvalidEctsException;
    Course updateCourse(Integer id, Course courseDetails) throws InvalidNameException, CourseInvalidEctsException;
    boolean deleteCourse(Integer id) throws CourseNotFoundException, CourseHasGradesException;
    Long countFailedStudents(Integer courseId) throws CourseNotFoundException;
    List<Course> getCoursesByEcts(Integer ects) throws CourseNotFoundException;
    List<Grade> getCourseGrades(Integer courseId) throws CourseNotFoundException, GradeNotFoundException;
}