# Student Grading System

A comprehensive Java Maven application for managing student grades, courses, and computing GPAs.

## Features

- **Student Management**: Add, remove, and retrieve students with unique IDs
- **Course Management**: Add, remove, and manage courses with credit hours
- **Grade Recording**: Record numerical grades (0-100) for student-course combinations
- **GPA Calculation**: Compute weighted GPAs based on credit hours and grade points
- **Course Averages**: Calculate average grades for specific courses
- **Data Integrity**: Comprehensive validation and error handling
- **Extensive Testing**: 61+ unit tests covering all functionality and edge cases

## Grade Scale

| Numerical Grade | Letter Grade | Grade Points |
|----------------|--------------|--------------|
| 90-100         | A            | 4.0          |
| 80-89          | B            | 3.0          |
| 70-79          | C            | 2.0          |
| 60-69          | D            | 1.0          |
| 0-59           | F            | 0.0          |

## Quick Start

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Build and Test

```bash
# Clone the repository
git clone <repository-url>
cd complex_software_1

# Build the project
mvn clean compile

# Run all tests
mvn test

# Create executable JAR
mvn package
```

### Running the Application

```bash
# Run the main application
mvn exec:java -Dexec.mainClass="com.gradingsystem.App"

# Or run the JAR directly
java -jar target/student-grading-system-1.0-SNAPSHOT.jar
```

## Usage Examples

### Basic Usage

```java
import com.gradingsystem.*;

// Create a grading system
GradingSystem gradingSystem = new GradingSystem();

// Add students
Student student1 = new Student("S001", "John Doe", "john.doe@example.com");
Student student2 = new Student("S002", "Jane Smith", "jane.smith@example.com");
gradingSystem.addStudent(student1);
gradingSystem.addStudent(student2);

// Add courses
Course cs101 = new Course("CS101", "Introduction to Computer Science", 3);
Course math101 = new Course("MATH101", "Calculus I", 4);
gradingSystem.addCourse(cs101);
gradingSystem.addCourse(math101);

// Record grades
gradingSystem.recordGrade("S001", "CS101", 95.0);   // A grade
gradingSystem.recordGrade("S001", "MATH101", 87.5); // B grade
gradingSystem.recordGrade("S002", "CS101", 92.0);   // A grade

// Calculate GPAs
double johnGPA = gradingSystem.calculateGPA("S001");
System.out.printf("John's GPA: %.2f%n", johnGPA);

// Calculate course averages
double cs101Average = gradingSystem.calculateCourseAverage("CS101");
System.out.printf("CS101 Average: %.2f%n", cs101Average);
```

### Advanced Features

```java
// Get all grades for a student
Collection<Grade> johnGrades = gradingSystem.getGradesForStudent("S001");
for (Grade grade : johnGrades) {
    System.out.printf("%s: %.1f (%s)%n", 
        grade.getCourse().getCourseCode(), 
        grade.getGradeValue(), 
        grade.getLetterGrade());
}

// Get all students enrolled in a course
Collection<Grade> cs101Grades = gradingSystem.getGradesForCourse("CS101");
for (Grade grade : cs101Grades) {
    System.out.printf("%s: %.1f%n", 
        grade.getStudent().getName(), 
        grade.getGradeValue());
}

// Update a grade (automatically replaces existing grade)
gradingSystem.recordGrade("S001", "CS101", 98.0);

// Remove a student (also removes all their grades)
gradingSystem.removeStudent("S002");

// Remove a course (also removes all grades for that course)
gradingSystem.removeCourse("MATH101");
```

## Architecture

### Core Classes

- **`Student`**: Represents a student with ID, name, and email
- **`Course`**: Represents a course with code, name, and credit hours
- **`Grade`**: Links a student to a course with a numerical grade
- **`GradingSystem`**: Main service class managing all operations

### Key Features

- **Immutable Domain Objects**: Student and Course objects are immutable for data integrity
- **Validation**: Comprehensive input validation with meaningful error messages
- **Case Insensitivity**: Course codes are handled case-insensitively
- **Automatic Cleanup**: Removing students or courses automatically removes associated grades
- **Grade Replacement**: Recording a new grade for the same student-course combination replaces the old grade

## Testing

The project includes comprehensive unit tests with 61+ test cases covering:

- **Unit Tests**: Individual class functionality
- **Integration Tests**: GradingSystem service operations
- **Edge Cases**: Boundary conditions and error scenarios
- **Validation Tests**: Input validation and error handling

Run tests with detailed output:

```bash
mvn test -Dtest.verbose=true
```

## Development

### Project Structure

```
src/
├── main/java/com/gradingsystem/
│   ├── App.java              # Main application class
│   ├── Student.java          # Student domain class
│   ├── Course.java           # Course domain class
│   ├── Grade.java            # Grade domain class
│   └── GradingSystem.java    # Main service class
└── test/java/com/gradingsystem/
    ├── AppTest.java          # App tests
    ├── StudentTest.java      # Student tests
    ├── CourseTest.java       # Course tests
    ├── GradeTest.java        # Grade tests
    └── GradingSystemTest.java # Integration tests
```

### Adding New Features

1. Create or modify domain classes in `src/main/java/com/gradingsystem/`
2. Add corresponding tests in `src/test/java/com/gradingsystem/`
3. Update `GradingSystem.java` if new operations are needed
4. Run tests to ensure nothing breaks: `mvn test`
5. Update this README if new public APIs are added

## Contributing

1. Fork the repository
2. Create a feature branch
3. Add comprehensive tests for new functionality
4. Ensure all tests pass: `mvn test`
5. Submit a pull request

## License

This project is part of the complex_software_1 repository.