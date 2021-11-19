package com.example.ist412se_v11_7.servicetest;
// Local project imports
import com.example.ist412se_v11_7.model.Student;
import com.example.ist412se_v11_7.repository.StudentRepository;
// JUnit imports
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
// Spring imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// List utility import
import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentServiceImplTest {
    @Autowired
    private StudentRepository repository;
    @Test
    void getAllStudents() {
        List<Student> students = repository.findAll(); // Find all students in repository
        assertEquals(2, students.size()); // Check for list size of 2 students
    }
    @Test
    public void testFindOne() {
        Student student = repository.findById(1L).get(); // Find first record with long data type
        assertEquals("Forrest Moulin", student.getStudName()); // Expected student name
    }
}
