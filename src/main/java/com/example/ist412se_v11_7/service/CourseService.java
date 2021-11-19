package com.example.ist412se_v11_7.service; // Package declaration

import com.example.ist412se_v11_7.model.Course; // Import course model class
// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Page.html
import org.springframework.data.domain.Page;
import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    void saveCourse(Course course);
    Course getCourseById(long id);
    void deleteCourseById(long id);
    Page<Course> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
