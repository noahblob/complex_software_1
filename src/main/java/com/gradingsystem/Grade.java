package com.gradingsystem;

import java.util.Objects;

/**
 * Represents a grade for a student in a specific course.
 */
public class Grade {
    private final Student student;
    private final Course course;
    private final double gradeValue;

    /**
     * Creates a new grade.
     *
     * @param student    the student who received the grade
     * @param course     the course for which the grade was given
     * @param gradeValue the numerical grade value (0.0 to 100.0)
     * @throws IllegalArgumentException if student or course is null, or gradeValue is invalid
     */
    public Grade(Student student, Course course, double gradeValue) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        if (gradeValue < 0.0 || gradeValue > 100.0) {
            throw new IllegalArgumentException("Grade value must be between 0.0 and 100.0");
        }

        this.student = student;
        this.course = course;
        this.gradeValue = gradeValue;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public double getGradeValue() {
        return gradeValue;
    }

    /**
     * Converts the numerical grade to a letter grade.
     *
     * @return the letter grade (A, B, C, D, F)
     */
    public String getLetterGrade() {
        if (gradeValue >= 90.0) return "A";
        if (gradeValue >= 80.0) return "B";
        if (gradeValue >= 70.0) return "C";
        if (gradeValue >= 60.0) return "D";
        return "F";
    }

    /**
     * Converts the numerical grade to grade points for GPA calculation.
     *
     * @return the grade points (4.0 for A, 3.0 for B, 2.0 for C, 1.0 for D, 0.0 for F)
     */
    public double getGradePoints() {
        if (gradeValue >= 90.0) return 4.0;
        if (gradeValue >= 80.0) return 3.0;
        if (gradeValue >= 70.0) return 2.0;
        if (gradeValue >= 60.0) return 1.0;
        return 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Double.compare(grade.gradeValue, gradeValue) == 0 &&
                Objects.equals(student, grade.student) &&
                Objects.equals(course, grade.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course, gradeValue);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "student=" + student.getName() +
                ", course=" + course.getCourseCode() +
                ", gradeValue=" + gradeValue +
                ", letterGrade='" + getLetterGrade() + '\'' +
                '}';
    }
}