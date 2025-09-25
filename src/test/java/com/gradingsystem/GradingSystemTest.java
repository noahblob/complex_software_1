package com.gradingsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;

/**
 * Unit tests for the GradingSystem class.
 */
public class GradingSystemTest {

    private GradingSystem gradingSystem;
    private Student student1;
    private Student student2;
    private Course course1;
    private Course course2;
    private Course course3;

    @BeforeEach
    public void setUp() {
        gradingSystem = new GradingSystem();
        student1 = new Student("S001", "John Doe", "john.doe@example.com");
        student2 = new Student("S002", "Jane Smith", "jane.smith@example.com");
        course1 = new Course("CS101", "Introduction to Computer Science", 3);
        course2 = new Course("MATH101", "Calculus I", 4);
        course3 = new Course("ENG101", "English Composition", 3);
    }

    @Test
    @DisplayName("Should add student successfully")
    public void testAddStudent() {
        gradingSystem.addStudent(student1);
        
        assertEquals(student1, gradingSystem.getStudent("S001"));
        assertTrue(gradingSystem.getAllStudents().contains(student1));
    }

    @Test
    @DisplayName("Should throw exception when adding null student")
    public void testAddNullStudent() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> gradingSystem.addStudent(null));
        
        assertEquals("Student cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when adding duplicate student")
    public void testAddDuplicateStudent() {
        gradingSystem.addStudent(student1);
        Student duplicate = new Student("S001", "Different Name", "different@example.com");
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> gradingSystem.addStudent(duplicate));
        
        assertEquals("Student with ID S001 already exists", exception.getMessage());
    }

    @Test
    @DisplayName("Should remove student successfully")
    public void testRemoveStudent() {
        gradingSystem.addStudent(student1);
        gradingSystem.addCourse(course1);
        gradingSystem.recordGrade("S001", "CS101", 85.0);
        
        assertTrue(gradingSystem.removeStudent("S001"));
        assertNull(gradingSystem.getStudent("S001"));
        assertTrue(gradingSystem.getAllGrades().isEmpty());
    }

    @Test
    @DisplayName("Should return false when removing non-existent student")
    public void testRemoveNonExistentStudent() {
        assertFalse(gradingSystem.removeStudent("S999"));
        assertFalse(gradingSystem.removeStudent(null));
        assertFalse(gradingSystem.removeStudent("  "));
    }

    @Test
    @DisplayName("Should add course successfully")
    public void testAddCourse() {
        gradingSystem.addCourse(course1);
        
        assertEquals(course1, gradingSystem.getCourse("CS101"));
        assertTrue(gradingSystem.getAllCourses().contains(course1));
    }

    @Test
    @DisplayName("Should throw exception when adding null course")
    public void testAddNullCourse() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> gradingSystem.addCourse(null));
        
        assertEquals("Course cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when adding duplicate course")
    public void testAddDuplicateCourse() {
        gradingSystem.addCourse(course1);
        Course duplicate = new Course("CS101", "Different Course", 4);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> gradingSystem.addCourse(duplicate));
        
        assertEquals("Course with code CS101 already exists", exception.getMessage());
    }

    @Test
    @DisplayName("Should remove course successfully")
    public void testRemoveCourse() {
        gradingSystem.addStudent(student1);
        gradingSystem.addCourse(course1);
        gradingSystem.recordGrade("S001", "CS101", 85.0);
        
        assertTrue(gradingSystem.removeCourse("CS101"));
        assertNull(gradingSystem.getCourse("CS101"));
        assertTrue(gradingSystem.getAllGrades().isEmpty());
    }

    @Test
    @DisplayName("Should return false when removing non-existent course")
    public void testRemoveNonExistentCourse() {
        assertFalse(gradingSystem.removeCourse("CS999"));
        assertFalse(gradingSystem.removeCourse(null));
        assertFalse(gradingSystem.removeCourse("  "));
    }

    @Test
    @DisplayName("Should record grade successfully")
    public void testRecordGrade() {
        gradingSystem.addStudent(student1);
        gradingSystem.addCourse(course1);
        
        gradingSystem.recordGrade("S001", "CS101", 85.0);
        
        Collection<Grade> grades = gradingSystem.getGradesForStudent("S001");
        assertEquals(1, grades.size());
        
        Grade grade = grades.iterator().next();
        assertEquals(student1, grade.getStudent());
        assertEquals(course1, grade.getCourse());
        assertEquals(85.0, grade.getGradeValue(), 0.001);
    }

    @Test
    @DisplayName("Should throw exception when recording grade for non-existent student")
    public void testRecordGradeForNonExistentStudent() {
        gradingSystem.addCourse(course1);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> gradingSystem.recordGrade("S999", "CS101", 85.0));
        
        assertEquals("Student with ID S999 not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when recording grade for non-existent course")
    public void testRecordGradeForNonExistentCourse() {
        gradingSystem.addStudent(student1);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> gradingSystem.recordGrade("S001", "CS999", 85.0));
        
        assertEquals("Course with code CS999 not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should replace existing grade when recording new grade for same student-course")
    public void testReplaceExistingGrade() {
        gradingSystem.addStudent(student1);
        gradingSystem.addCourse(course1);
        
        gradingSystem.recordGrade("S001", "CS101", 85.0);
        gradingSystem.recordGrade("S001", "CS101", 90.0);
        
        Collection<Grade> grades = gradingSystem.getGradesForStudent("S001");
        assertEquals(1, grades.size());
        
        Grade grade = grades.iterator().next();
        assertEquals(90.0, grade.getGradeValue(), 0.001);
    }

    @Test
    @DisplayName("Should calculate GPA correctly")
    public void testCalculateGPA() {
        gradingSystem.addStudent(student1);
        gradingSystem.addCourse(course1); // 3 credits
        gradingSystem.addCourse(course2); // 4 credits
        
        gradingSystem.recordGrade("S001", "CS101", 90.0);   // A (4.0)
        gradingSystem.recordGrade("S001", "MATH101", 80.0); // B (3.0)
        
        // GPA = (4.0 * 3 + 3.0 * 4) / (3 + 4) = (12 + 12) / 7 = 24/7 ≈ 3.43
        double expectedGPA = 24.0 / 7.0;
        assertEquals(expectedGPA, gradingSystem.calculateGPA("S001"), 0.001);
    }

    @Test
    @DisplayName("Should return 0.0 GPA when no grades recorded")
    public void testCalculateGPAWithNoGrades() {
        gradingSystem.addStudent(student1);
        
        assertEquals(0.0, gradingSystem.calculateGPA("S001"), 0.001);
    }

    @Test
    @DisplayName("Should throw exception when calculating GPA for non-existent student")
    public void testCalculateGPAForNonExistentStudent() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> gradingSystem.calculateGPA("S999"));
        
        assertEquals("Student with ID S999 not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should calculate course average correctly")
    public void testCalculateCourseAverage() {
        gradingSystem.addStudent(student1);
        gradingSystem.addStudent(student2);
        gradingSystem.addCourse(course1);
        
        gradingSystem.recordGrade("S001", "CS101", 90.0);
        gradingSystem.recordGrade("S002", "CS101", 80.0);
        
        // Average = (90.0 + 80.0) / 2 = 85.0
        assertEquals(85.0, gradingSystem.calculateCourseAverage("CS101"), 0.001);
    }

    @Test
    @DisplayName("Should return 0.0 when calculating average for course with no grades")
    public void testCalculateCourseAverageWithNoGrades() {
        gradingSystem.addCourse(course1);
        
        assertEquals(0.0, gradingSystem.calculateCourseAverage("CS101"), 0.001);
    }

    @Test
    @DisplayName("Should throw exception when calculating average for non-existent course")
    public void testCalculateCourseAverageForNonExistentCourse() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> gradingSystem.calculateCourseAverage("CS999"));
        
        assertEquals("Course with code CS999 not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should handle case insensitive course codes")
    public void testCaseInsensitiveCourseOperations() {
        gradingSystem.addStudent(student1);
        gradingSystem.addCourse(course1);
        
        // Record grade with lowercase course code
        gradingSystem.recordGrade("S001", "cs101", 85.0);
        
        // Calculate average with mixed case
        assertEquals(85.0, gradingSystem.calculateCourseAverage("Cs101"), 0.001);
        
        // Get grades with different case
        assertEquals(1, gradingSystem.getGradesForCourse("CS101").size());
        assertEquals(1, gradingSystem.getGradesForCourse("cs101").size());
    }

    @Test
    @DisplayName("Should get grades for student")
    public void testGetGradesForStudent() {
        gradingSystem.addStudent(student1);
        gradingSystem.addCourse(course1);
        gradingSystem.addCourse(course2);
        
        gradingSystem.recordGrade("S001", "CS101", 85.0);
        gradingSystem.recordGrade("S001", "MATH101", 90.0);
        
        Collection<Grade> grades = gradingSystem.getGradesForStudent("S001");
        assertEquals(2, grades.size());
    }

    @Test
    @DisplayName("Should get grades for course")
    public void testGetGradesForCourse() {
        gradingSystem.addStudent(student1);
        gradingSystem.addStudent(student2);
        gradingSystem.addCourse(course1);
        
        gradingSystem.recordGrade("S001", "CS101", 85.0);
        gradingSystem.recordGrade("S002", "CS101", 90.0);
        
        Collection<Grade> grades = gradingSystem.getGradesForCourse("CS101");
        assertEquals(2, grades.size());
    }

    @Test
    @DisplayName("Should get all students, courses, and grades")
    public void testGetAllCollections() {
        gradingSystem.addStudent(student1);
        gradingSystem.addStudent(student2);
        gradingSystem.addCourse(course1);
        gradingSystem.addCourse(course2);
        gradingSystem.recordGrade("S001", "CS101", 85.0);
        
        assertEquals(2, gradingSystem.getAllStudents().size());
        assertEquals(2, gradingSystem.getAllCourses().size());
        assertEquals(1, gradingSystem.getAllGrades().size());
    }

    @Test
    @DisplayName("Should calculate complex GPA scenario")
    public void testComplexGPACalculation() {
        gradingSystem.addStudent(student1);
        gradingSystem.addCourse(course1); // 3 credits
        gradingSystem.addCourse(course2); // 4 credits
        gradingSystem.addCourse(course3); // 3 credits
        
        gradingSystem.recordGrade("S001", "CS101", 95.0);   // A (4.0) * 3 = 12.0
        gradingSystem.recordGrade("S001", "MATH101", 75.0); // C (2.0) * 4 = 8.0
        gradingSystem.recordGrade("S001", "ENG101", 85.0);  // B (3.0) * 3 = 9.0
        
        // GPA = (12.0 + 8.0 + 9.0) / (3 + 4 + 3) = 29.0 / 10 = 2.9
        assertEquals(2.9, gradingSystem.calculateGPA("S001"), 0.001);
    }

    @Test
    @DisplayName("Should handle failing grades in GPA calculation")
    public void testGPACalculationWithFailingGrades() {
        gradingSystem.addStudent(student1);
        gradingSystem.addCourse(course1); // 3 credits
        gradingSystem.addCourse(course2); // 4 credits
        
        gradingSystem.recordGrade("S001", "CS101", 50.0);   // F (0.0) * 3 = 0.0
        gradingSystem.recordGrade("S001", "MATH101", 90.0); // A (4.0) * 4 = 16.0
        
        // GPA = (0.0 + 16.0) / (3 + 4) = 16.0 / 7 ≈ 2.286
        assertEquals(16.0 / 7.0, gradingSystem.calculateGPA("S001"), 0.001);
    }
}