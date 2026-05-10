package com.example.SpringBootApp.model;

import jakarta.persistence.*;

@Entity
public class Kurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int ects;

    public Kurs() {}

    public Kurs(Integer id, String name, int ects) {
        this.id = id;
        this.name = name;
        this.ects = ects;
    }

    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getEcts() {
        return ects;
    }
    public void setEcts(int ects) {
        this.ects = ects;
    }
}