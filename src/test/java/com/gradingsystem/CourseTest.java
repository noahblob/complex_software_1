package com.gradingsystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Course class.
 */
public class CourseTest {

    @Test
    @DisplayName("Should create course with valid parameters")
    public void testCreateValidCourse() {
        Course course = new Course("CS101", "Introduction to Computer Science", 3);
        
        assertEquals("CS101", course.getCourseCode());
        assertEquals("Introduction to Computer Science", course.getCourseName());
        assertEquals(3, course.getCredits());
    }

    @Test
    @DisplayName("Should trim and uppercase course code")
    public void testCreateCourseWithWhitespaceAndLowercase() {
        Course course = new Course(" cs101 ", " Introduction to Computer Science ", 3);
        
        assertEquals("CS101", course.getCourseCode());
        assertEquals("Introduction to Computer Science", course.getCourseName());
        assertEquals(3, course.getCredits());
    }

    @Test
    @DisplayName("Should throw exception for null course code")
    public void testCreateCourseWithNullCode() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Course(null, "Introduction to Computer Science", 3));
        
        assertEquals("Course code cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for empty course code")
    public void testCreateCourseWithEmptyCode() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Course("  ", "Introduction to Computer Science", 3));
        
        assertEquals("Course code cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for null course name")
    public void testCreateCourseWithNullName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Course("CS101", null, 3));
        
        assertEquals("Course name cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for empty course name")
    public void testCreateCourseWithEmptyName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Course("CS101", "  ", 3));
        
        assertEquals("Course name cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for zero credits")
    public void testCreateCourseWithZeroCredits() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Course("CS101", "Introduction to Computer Science", 0));
        
        assertEquals("Credits must be positive", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for negative credits")
    public void testCreateCourseWithNegativeCredits() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new Course("CS101", "Introduction to Computer Science", -1));
        
        assertEquals("Credits must be positive", exception.getMessage());
    }

    @Test
    @DisplayName("Should allow various credit values")
    public void testCreateCourseWithDifferentCredits() {
        Course course1 = new Course("CS101", "Intro to CS", 1);
        Course course2 = new Course("CS102", "Advanced CS", 4);
        Course course3 = new Course("CS103", "Project Course", 6);
        
        assertEquals(1, course1.getCredits());
        assertEquals(4, course2.getCredits());
        assertEquals(6, course3.getCredits());
    }

    @Test
    @DisplayName("Should implement equals correctly")
    public void testCourseEquals() {
        Course course1 = new Course("CS101", "Introduction to Computer Science", 3);
        Course course2 = new Course("CS101", "Intro to CS", 4);
        Course course3 = new Course("CS102", "Introduction to Computer Science", 3);

        // Same course code should be equal (regardless of name or credits)
        assertEquals(course1, course2);
        
        // Different course code should not be equal
        assertNotEquals(course1, course3);
        
        // Self equality
        assertEquals(course1, course1);
        
        // Null comparison
        assertNotEquals(course1, null);
        
        // Different class comparison
        assertNotEquals(course1, "not a course");
    }

    @Test
    @DisplayName("Should implement hashCode correctly")
    public void testCourseHashCode() {
        Course course1 = new Course("CS101", "Introduction to Computer Science", 3);
        Course course2 = new Course("CS101", "Intro to CS", 4);
        Course course3 = new Course("CS102", "Introduction to Computer Science", 3);

        // Same course code should have same hash code
        assertEquals(course1.hashCode(), course2.hashCode());
        
        // Different course codes should have different hash codes
        assertNotEquals(course1.hashCode(), course3.hashCode());
    }

    @Test
    @DisplayName("Should have meaningful toString representation")
    public void testCourseToString() {
        Course course = new Course("CS101", "Introduction to Computer Science", 3);
        String toString = course.toString();
        
        assertTrue(toString.contains("CS101"));
        assertTrue(toString.contains("Introduction to Computer Science"));
        assertTrue(toString.contains("3"));
        assertTrue(toString.contains("Course"));
    }
}