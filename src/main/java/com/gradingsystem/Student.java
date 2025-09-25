package com.gradingsystem;

import java.util.Objects;

/**
 * Represents a student in the grading system.
 */
public class Student {
    private final String studentId;
    private final String name;
    private final String email;

    /**
     * Creates a new student.
     *
     * @param studentId unique identifier for the student
     * @param name      student's full name
     * @param email     student's email address
     * @throws IllegalArgumentException if any parameter is null or empty
     */
    public Student(String studentId, String name, String email) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        this.studentId = studentId.trim();
        this.name = name.trim();
        this.email = email.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(studentId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}