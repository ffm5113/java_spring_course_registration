package com.example.ist412se_v11_7.service;

import com.example.ist412se_v11_7.model.Student; // Import student model class
// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Page.html
import org.springframework.data.domain.Page;
import java.util.List; // Used for list of students

public interface StudentService {
    List<Student> getAllStudents();
    void saveStudent(Student student);
    Student getStudentById(long sid);
    void deleteStudentById(long sid);
    Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
