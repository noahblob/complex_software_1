package com.gradingsystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Student class.
 */
public class StudentTest {

    @Test
    @DisplayName("Should create student with valid parameters")
    public void testCreateValidStudent() {
        Student student = new Student("S001", "John Doe", "john.doe@example.com");
        
        assertEquals("S001", student.getStudentId());
        assertEquals("John Doe", student.getName());
        assertEquals("john.doe@example.com", student.getEmail());
    }

    @Test
    @DisplayName("Should trim whitespace from parameters")
    public void testCreateStudentWithWhitespace() {
        Student student = new Student(" S001 ", " John Doe ", " john.doe@example.com ");
        
        assertEquals("S001", student.getStudentId());
        assertEquals("John Doe", student.getName());
        assertEquals("john.doe@example.com", student.getEmail());
    }

    @Test
    @DisplayName("Should throw exception for null student ID")
    public void testCreateStudentWithNullId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Student(null, "John Doe", "john.doe@example.com"));
        
        assertEquals("Student ID cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for empty student ID")
    public void testCreateStudentWithEmptyId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Student("  ", "John Doe", "john.doe@example.com"));
        
        assertEquals("Student ID cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for null name")
    public void testCreateStudentWithNullName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Student("S001", null, "john.doe@example.com"));
        
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for empty name")
    public void testCreateStudentWithEmptyName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Student("S001", "  ", "john.doe@example.com"));
        
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for null email")
    public void testCreateStudentWithNullEmail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Student("S001", "John Doe", null));
        
        assertEquals("Email cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for empty email")
    public void testCreateStudentWithEmptyEmail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Student("S001", "John Doe", "  "));
        
        assertEquals("Email cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should implement equals correctly")
    public void testStudentEquals() {
        Student student1 = new Student("S001", "John Doe", "john.doe@example.com");
        Student student2 = new Student("S001", "Jane Doe", "jane.doe@example.com");
        Student student3 = new Student("S002", "John Doe", "john.doe@example.com");

        // Same student ID should be equal
        assertEquals(student1, student2);
        
        // Different student ID should not be equal
        assertNotEquals(student1, student3);
        
        // Self equality
        assertEquals(student1, student1);
        
        // Null comparison
        assertNotEquals(student1, null);
        
        // Different class comparison
        assertNotEquals(student1, "not a student");
    }

    @Test
    @DisplayName("Should implement hashCode correctly")
    public void testStudentHashCode() {
        Student student1 = new Student("S001", "John Doe", "john.doe@example.com");
        Student student2 = new Student("S001", "Jane Doe", "jane.doe@example.com");
        Student student3 = new Student("S002", "John Doe", "john.doe@example.com");

        // Same student ID should have same hash code
        assertEquals(student1.hashCode(), student2.hashCode());
        
        // Different student IDs may have different hash codes
        assertNotEquals(student1.hashCode(), student3.hashCode());
    }

    @Test
    @DisplayName("Should have meaningful toString representation")
    public void testStudentToString() {
        Student student = new Student("S001", "John Doe", "john.doe@example.com");
        String toString = student.toString();
        
        assertTrue(toString.contains("S001"));
        assertTrue(toString.contains("John Doe"));
        assertTrue(toString.contains("john.doe@example.com"));
        assertTrue(toString.contains("Student"));
    }
}