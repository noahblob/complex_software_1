package com.gradingsystem;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Main service class for managing the student grading system.
 * Provides functionality to manage students, courses, grades, and compute GPAs.
 */
public class GradingSystem {
    private final Map<String, Student> students;
    private final Map<String, Course> courses;
    private final List<Grade> grades;

    /**
     * Creates a new grading system.
     */
    public GradingSystem() {
        this.students = new HashMap<>();
        this.courses = new HashMap<>();
        this.grades = new ArrayList<>();
    }

    /**
     * Adds a student to the system.
     *
     * @param student the student to add
     * @throws IllegalArgumentException if student is null or already exists
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (students.containsKey(student.getStudentId())) {
            throw new IllegalArgumentException("Student with ID " + student.getStudentId() + " already exists");
        }
        students.put(student.getStudentId(), student);
    }

    /**
     * Removes a student from the system.
     *
     * @param studentId the ID of the student to remove
     * @return true if the student was removed, false if not found
     */
    public boolean removeStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return false;
        }
        
        // Remove all grades for this student
        grades.removeIf(grade -> grade.getStudent().getStudentId().equals(studentId));
        
        return students.remove(studentId) != null;
    }

    /**
     * Adds a course to the system.
     *
     * @param course the course to add
     * @throws IllegalArgumentException if course is null or already exists
     */
    public void addCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        if (courses.containsKey(course.getCourseCode())) {
            throw new IllegalArgumentException("Course with code " + course.getCourseCode() + " already exists");
        }
        courses.put(course.getCourseCode(), course);
    }

    /**
     * Removes a course from the system.
     *
     * @param courseCode the code of the course to remove
     * @return true if the course was removed, false if not found
     */
    public boolean removeCourse(String courseCode) {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            return false;
        }
        
        String normalizedCode = courseCode.trim().toUpperCase();
        
        // Remove all grades for this course
        grades.removeIf(grade -> grade.getCourse().getCourseCode().equals(normalizedCode));
        
        return courses.remove(normalizedCode) != null;
    }

    /**
     * Records a grade for a student in a course.
     *
     * @param studentId   the student's ID
     * @param courseCode  the course code
     * @param gradeValue  the numerical grade value
     * @throws IllegalArgumentException if student or course doesn't exist, or grade is invalid
     */
    public void recordGrade(String studentId, String courseCode, double gradeValue) {
        Student student = students.get(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student with ID " + studentId + " not found");
        }
        
        String normalizedCourseCode = courseCode.trim().toUpperCase();
        Course course = courses.get(normalizedCourseCode);
        if (course == null) {
            throw new IllegalArgumentException("Course with code " + courseCode + " not found");
        }

        // Remove any existing grade for this student-course combination
        grades.removeIf(grade -> 
            grade.getStudent().getStudentId().equals(studentId) &&
            grade.getCourse().getCourseCode().equals(normalizedCourseCode)
        );

        // Add the new grade
        grades.add(new Grade(student, course, gradeValue));
    }

    /**
     * Calculates the GPA for a specific student.
     *
     * @param studentId the student's ID
     * @return the student's GPA, or 0.0 if no grades found
     * @throws IllegalArgumentException if student doesn't exist
     */
    public double calculateGPA(String studentId) {
        if (!students.containsKey(studentId)) {
            throw new IllegalArgumentException("Student with ID " + studentId + " not found");
        }

        List<Grade> studentGrades = grades.stream()
                .filter(grade -> grade.getStudent().getStudentId().equals(studentId))
                .collect(Collectors.toList());

        if (studentGrades.isEmpty()) {
            return 0.0;
        }

        double totalGradePoints = 0.0;
        int totalCredits = 0;

        for (Grade grade : studentGrades) {
            double gradePoints = grade.getGradePoints();
            int credits = grade.getCourse().getCredits();
            totalGradePoints += gradePoints * credits;
            totalCredits += credits;
        }

        return totalCredits > 0 ? totalGradePoints / totalCredits : 0.0;
    }

    /**
     * Calculates the average grade for a specific course.
     *
     * @param courseCode the course code
     * @return the average grade for the course, or 0.0 if no grades found
     * @throws IllegalArgumentException if course doesn't exist
     */
    public double calculateCourseAverage(String courseCode) {
        String normalizedCourseCode = courseCode.trim().toUpperCase();
        if (!courses.containsKey(normalizedCourseCode)) {
            throw new IllegalArgumentException("Course with code " + courseCode + " not found");
        }

        List<Grade> courseGrades = grades.stream()
                .filter(grade -> grade.getCourse().getCourseCode().equals(normalizedCourseCode))
                .collect(Collectors.toList());

        if (courseGrades.isEmpty()) {
            return 0.0;
        }

        return courseGrades.stream()
                .mapToDouble(Grade::getGradeValue)
                .average()
                .orElse(0.0);
    }

    /**
     * Gets all students in the system.
     *
     * @return a collection of all students
     */
    public Collection<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    /**
     * Gets all courses in the system.
     *
     * @return a collection of all courses
     */
    public Collection<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }

    /**
     * Gets all grades in the system.
     *
     * @return a collection of all grades
     */
    public Collection<Grade> getAllGrades() {
        return new ArrayList<>(grades);
    }

    /**
     * Gets all grades for a specific student.
     *
     * @param studentId the student's ID
     * @return a collection of grades for the student
     */
    public Collection<Grade> getGradesForStudent(String studentId) {
        return grades.stream()
                .filter(grade -> grade.getStudent().getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    /**
     * Gets all grades for a specific course.
     *
     * @param courseCode the course code
     * @return a collection of grades for the course
     */
    public Collection<Grade> getGradesForCourse(String courseCode) {
        String normalizedCourseCode = courseCode.trim().toUpperCase();
        return grades.stream()
                .filter(grade -> grade.getCourse().getCourseCode().equals(normalizedCourseCode))
                .collect(Collectors.toList());
    }

    /**
     * Gets a student by ID.
     *
     * @param studentId the student's ID
     * @return the student, or null if not found
     */
    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    /**
     * Gets a course by code.
     *
     * @param courseCode the course code
     * @return the course, or null if not found
     */
    public Course getCourse(String courseCode) {
        return courses.get(courseCode.trim().toUpperCase());
    }
}