package com.example.SpringBootApp.service;

import com.example.SpringBootApp.exception.*;
import com.example.SpringBootApp.model.Course;
import com.example.SpringBootApp.model.Grade;
import com.example.SpringBootApp.repository.CourseRepository;
import com.example.SpringBootApp.repository.GradeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final GradeRepository gradeRepository;

    public CourseServiceImpl(CourseRepository courseRepository, GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course addCourse(Course course) throws InvalidNameException, CourseInvalidEctsException {
        if (course.getName() == null || course.getName().trim().isEmpty()) {
            throw new InvalidNameException();
        }

        if (course.getEcts() == null || course.getEcts() < 0 || course.getEcts() > 30) {
            throw new CourseInvalidEctsException();
        }
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Integer id, Course courseDetails) throws CourseNotFoundException, InvalidNameException, CourseInvalidEctsException {
        if (courseDetails.getName() == null || courseDetails.getName().trim().isEmpty()) {
            throw new InvalidNameException();
        }
        if (courseDetails.getEcts() == null || courseDetails.getEcts() < 0 || courseDetails.getEcts() > 30) {
            throw new CourseInvalidEctsException();
        }
        Course course = courseRepository.findById(id)
                .orElseThrow(CourseNotFoundException::new);
        course.setName(courseDetails.getName());
        course.setEcts(courseDetails.getEcts());
        return courseRepository.save(course);
    }

    @Override
    public boolean deleteCourse(Integer id) throws CourseNotFoundException, CourseHasGradesException {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException();
        }

        if (gradeRepository.existsByCourseId(id)) {
            throw new CourseHasGradesException();
        }

        courseRepository.deleteById(id);
        return true;
    }

    @Override
    public Long countFailedStudents(Integer courseId) throws CourseNotFoundException {
        return gradeRepository.countByCourseIdAndGrade(courseId, 2.0);
    }

    @Override
    public List<Course> getCoursesByEcts(Integer ects) throws CourseNotFoundException{

        List<Course> courses = courseRepository.findByEcts(ects);
        if (courses.isEmpty()) throw new CourseNotFoundException();
        return courses;
    }

    @Override
    public List<Grade> getCourseGrades(Integer courseId) throws CourseNotFoundException, GradeNotFoundException {
        if (!courseRepository.existsById(courseId)) {
            throw new CourseNotFoundException();
        }

        List<Grade> grades = gradeRepository.findByCourseId(courseId);
        if (grades.isEmpty()) {
            throw new GradeNotFoundException();
        }
        return grades;
    }
}