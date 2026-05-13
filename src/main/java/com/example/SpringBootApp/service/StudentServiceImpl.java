package com.example.SpringBootApp.service;

import com.example.SpringBootApp.exception.*;
import com.example.SpringBootApp.model.Grade;
import com.example.SpringBootApp.model.Student;
import com.example.SpringBootApp.repository.GradeRepository;
import com.example.SpringBootApp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    public StudentServiceImpl(StudentRepository studentRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(Integer id) throws StudentNotFoundException {
        return studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public Student addStudent(Student student) throws StudentInvalidAgeException, InvalidNameException {
        if (student.getAge() <= 0 || student.getAge() > 123) throw new StudentInvalidAgeException();
        if (student.getFirstName() == null || student.getFirstName().isEmpty()) throw new InvalidNameException();
        return studentRepository.save(student);
    }

    @Override
    public boolean deleteStudent(Integer id) throws StudentNotFoundException {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException();
        }
        studentRepository.deleteById(id);
        return true;
    }

    @Override
    public Student updateStudent(Integer id, Student studentDetails) throws StudentNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
        student.setFirstName(studentDetails.getFirstName());
        student.setAge(studentDetails.getAge());
        student.setCity(studentDetails.getCity());
        return studentRepository.save(student);
    }



    //biznesówki
    @Override
    public int countStudents() {
        return (int) studentRepository.count();
    }

    @Override
    public Double getAverage(Integer id) throws StudentNotFoundException {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException();
        }

        List<Grade> grades = gradeRepository.findByStudentId(id);

        if (grades.isEmpty()) return 0.0;

        double sumProd = 0;
        int sumEcts = 0;

        for (Grade g : grades) {
            if (g.getCourse().getEcts() != null) {
                sumProd += g.getGrade() * g.getCourse().getEcts();
                sumEcts += g.getCourse().getEcts();
            }
        }

        if (sumEcts == 0) return 0.0;

        return sumProd / sumEcts;
    }

    @Override
    public List<Grade> getStudentGrades(Integer id) throws StudentNotFoundException, StudentNoGradesException {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException();
        }
        List<Grade> grades = gradeRepository.findByStudentId(id);
        if (grades.isEmpty()) throw new StudentNoGradesException();
        return gradeRepository.findByStudentId(id);
    }

    @Override
    public List<Student> getTopStudents() throws StudentNotFoundException {
        List<Student> allStudents = studentRepository.findAll();

        List<Student> topStudents = allStudents.stream()
                .filter(s -> {
                    try {
                        Double avg = getAverage(s.getId());
                        return avg >= 4.75;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .toList();

        if (topStudents.isEmpty()) {
            throw new StudentNotFoundException();
        }
        return topStudents;
    }

}