package com.gradingsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Grade class.
 */
public class GradeTest {

    private Student student;
    private Course course;

    @BeforeEach
    public void setUp() {
        student = new Student("S001", "John Doe", "john.doe@example.com");
        course = new Course("CS101", "Introduction to Computer Science", 3);
    }

    @Test
    @DisplayName("Should create grade with valid parameters")
    public void testCreateValidGrade() {
        Grade grade = new Grade(student, course, 85.5);
        
        assertEquals(student, grade.getStudent());
        assertEquals(course, grade.getCourse());
        assertEquals(85.5, grade.getGradeValue(), 0.001);
    }

    @Test
    @DisplayName("Should throw exception for null student")
    public void testCreateGradeWithNullStudent() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Grade(null, course, 85.5));
        
        assertEquals("Student cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for null course")
    public void testCreateGradeWithNullCourse() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Grade(student, null, 85.5));
        
        assertEquals("Course cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for negative grade value")
    public void testCreateGradeWithNegativeValue() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Grade(student, course, -1.0));
        
        assertEquals("Grade value must be between 0.0 and 100.0", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for grade value over 100")
    public void testCreateGradeWithValueOver100() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Grade(student, course, 101.0));
        
        assertEquals("Grade value must be between 0.0 and 100.0", exception.getMessage());
    }

    @Test
    @DisplayName("Should allow boundary grade values")
    public void testCreateGradeWithBoundaryValues() {
        Grade grade1 = new Grade(student, course, 0.0);
        Grade grade2 = new Grade(student, course, 100.0);
        
        assertEquals(0.0, grade1.getGradeValue(), 0.001);
        assertEquals(100.0, grade2.getGradeValue(), 0.001);
    }

    @Test
    @DisplayName("Should convert numerical grade to correct letter grade")
    public void testGetLetterGrade() {
        assertEquals("A", new Grade(student, course, 95.0).getLetterGrade());
        assertEquals("A", new Grade(student, course, 90.0).getLetterGrade());
        assertEquals("B", new Grade(student, course, 89.9).getLetterGrade());
        assertEquals("B", new Grade(student, course, 85.0).getLetterGrade());
        assertEquals("B", new Grade(student, course, 80.0).getLetterGrade());
        assertEquals("C", new Grade(student, course, 79.9).getLetterGrade());
        assertEquals("C", new Grade(student, course, 75.0).getLetterGrade());
        assertEquals("C", new Grade(student, course, 70.0).getLetterGrade());
        assertEquals("D", new Grade(student, course, 69.9).getLetterGrade());
        assertEquals("D", new Grade(student, course, 65.0).getLetterGrade());
        assertEquals("D", new Grade(student, course, 60.0).getLetterGrade());
        assertEquals("F", new Grade(student, course, 59.9).getLetterGrade());
        assertEquals("F", new Grade(student, course, 50.0).getLetterGrade());
        assertEquals("F", new Grade(student, course, 0.0).getLetterGrade());
    }

    @Test
    @DisplayName("Should convert numerical grade to correct grade points")
    public void testGetGradePoints() {
        assertEquals(4.0, new Grade(student, course, 95.0).getGradePoints(), 0.001);
        assertEquals(4.0, new Grade(student, course, 90.0).getGradePoints(), 0.001);
        assertEquals(3.0, new Grade(student, course, 89.9).getGradePoints(), 0.001);
        assertEquals(3.0, new Grade(student, course, 85.0).getGradePoints(), 0.001);
        assertEquals(3.0, new Grade(student, course, 80.0).getGradePoints(), 0.001);
        assertEquals(2.0, new Grade(student, course, 79.9).getGradePoints(), 0.001);
        assertEquals(2.0, new Grade(student, course, 75.0).getGradePoints(), 0.001);
        assertEquals(2.0, new Grade(student, course, 70.0).getGradePoints(), 0.001);
        assertEquals(1.0, new Grade(student, course, 69.9).getGradePoints(), 0.001);
        assertEquals(1.0, new Grade(student, course, 65.0).getGradePoints(), 0.001);
        assertEquals(1.0, new Grade(student, course, 60.0).getGradePoints(), 0.001);
        assertEquals(0.0, new Grade(student, course, 59.9).getGradePoints(), 0.001);
        assertEquals(0.0, new Grade(student, course, 50.0).getGradePoints(), 0.001);
        assertEquals(0.0, new Grade(student, course, 0.0).getGradePoints(), 0.001);
    }

    @Test
    @DisplayName("Should implement equals correctly")
    public void testGradeEquals() {
        Student student2 = new Student("S002", "Jane Doe", "jane.doe@example.com");
        Course course2 = new Course("CS102", "Data Structures", 4);

        Grade grade1 = new Grade(student, course, 85.5);
        Grade grade2 = new Grade(student, course, 85.5);
        Grade grade3 = new Grade(student2, course, 85.5);
        Grade grade4 = new Grade(student, course2, 85.5);
        Grade grade5 = new Grade(student, course, 90.0);

        // Same student, course, and grade should be equal
        assertEquals(grade1, grade2);
        
        // Different student should not be equal
        assertNotEquals(grade1, grade3);
        
        // Different course should not be equal
        assertNotEquals(grade1, grade4);
        
        // Different grade value should not be equal
        assertNotEquals(grade1, grade5);
        
        // Self equality
        assertEquals(grade1, grade1);
        
        // Null comparison
        assertNotEquals(grade1, null);
        
        // Different class comparison
        assertNotEquals(grade1, "not a grade");
    }

    @Test
    @DisplayName("Should implement hashCode correctly")
    public void testGradeHashCode() {
        Grade grade1 = new Grade(student, course, 85.5);
        Grade grade2 = new Grade(student, course, 85.5);

        // Equal grades should have same hash code
        assertEquals(grade1.hashCode(), grade2.hashCode());
    }

    @Test
    @DisplayName("Should have meaningful toString representation")
    public void testGradeToString() {
        Grade grade = new Grade(student, course, 85.5);
        String toString = grade.toString();
        
        assertTrue(toString.contains("John Doe"));
        assertTrue(toString.contains("CS101"));
        assertTrue(toString.contains("85.5"));
        assertTrue(toString.contains("B"));
        assertTrue(toString.contains("Grade"));
    }
}