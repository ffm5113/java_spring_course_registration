package com.example.ist412se_v11_7.service;

import java.util.List; // Used for list of courses
import java.util.Optional; // Optional used to contain null and/or not-null values
// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Page.html
import org.springframework.data.domain.Page; // Used to define findPaginated method return type
import com.example.ist412se_v11_7.model.Course; // Import course model class
import com.example.ist412se_v11_7.repository.CourseRepository; // Import course repository class
import org.springframework.beans.factory.annotation.Autowired; // Autowired annotations
import org.springframework.stereotype.Service; // Service annotations
// Used in findPaginated method
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() { return courseRepository.findAll();}

    @Override
    public void saveCourse(Course course) { this.courseRepository.save(course);}

    @Override
    public Course getCourseById(long id) {
        Optional<Course> optional = courseRepository.findById(id);
        Course course = null;
        if (optional.isPresent()) {
            course = optional.get();
        }
        else {
            throw new RuntimeException("Course not found for id: " + id);
        }
        return course;
    }

    @Override
    public void deleteCourseById(long id) { this.courseRepository.deleteById(id);}

    @Override
    public Page<Course> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.courseRepository.findAll(pageable);
    }
}
