package com.gradingsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the main App class.
 */
public class AppTest {

    @Test
    public void testMainMethod() {
        // This test ensures main method runs without throwing exceptions
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }
}
