package com.gradingsystem;

import java.util.Objects;

/**
 * Represents a course in the grading system.
 */
public class Course {
    private final String courseCode;
    private final String courseName;
    private final int credits;

    /**
     * Creates a new course.
     *
     * @param courseCode unique identifier for the course (e.g., "CS101")
     * @param courseName descriptive name of the course
     * @param credits    number of credit hours for the course
     * @throws IllegalArgumentException if courseCode or courseName is null/empty, or credits is non-positive
     */
    public Course(String courseCode, String courseName, int credits) {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be null or empty");
        }
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be null or empty");
        }
        if (credits <= 0) {
            throw new IllegalArgumentException("Credits must be positive");
        }

        this.courseCode = courseCode.trim().toUpperCase();
        this.courseName = courseName.trim();
        this.credits = credits;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseCode, course.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(courseCode);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                '}';
    }
}