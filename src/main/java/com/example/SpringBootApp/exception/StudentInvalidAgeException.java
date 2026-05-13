package com.example.SpringBootApp.exception;
public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException() {
        super("Invalid Age");
    }
}