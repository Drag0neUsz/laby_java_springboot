package com.example.SpringBootApp.service;

import com.example.SpringBootApp.exception.*;
import com.example.SpringBootApp.model.Student;
import com.example.SpringBootApp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public Student getStudent(Integer id) throws StudentNotFoundException {
        return repository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public Student addStudent(Student student) throws InvalidAgeException, InvalidNameException {
        if (student.getAge() <= 0 || student.getAge() > 123) throw new InvalidAgeException();
        if (student.getFirstName() == null || student.getFirstName().isEmpty()) throw new InvalidNameException();
        return repository.save(student);
    }

    @Override
    public boolean deleteStudent(Integer id) throws StudentNotFoundException {
        if (!repository.existsById(id)) {
            throw new StudentNotFoundException();
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public Student updateStudent(Integer id, Student studentDetails) throws StudentNotFoundException {
        Student student = repository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
        student.setFirstName(studentDetails.getFirstName());
        student.setAge(studentDetails.getAge());
        student.setCity(studentDetails.getCity());
        return repository.save(student);
    }

    @Override
    public int countStudents() {
        // count() to kolejny gotowiec z repozytorium
        return (int) repository.count();
    }

}