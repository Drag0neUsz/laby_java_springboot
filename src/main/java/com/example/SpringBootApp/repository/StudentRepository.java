package com.example.SpringBootApp.repository;
import com.example.SpringBootApp.model.Grade;
import com.example.SpringBootApp.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
}