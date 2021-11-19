package com.example.ist412se_v11_7.repository; // Package declaration

import com.example.ist412se_v11_7.model.Course; // Import course model class
import org.springframework.data.jpa.repository.JpaRepository; // For CourseRepository class to extend JpaRepository
import org.springframework.stereotype.Repository; // Used in the creation of a course repository

import java.util.Optional; // Optional used to contain null and/or not-null values

@Repository // Create course repository via spring framework stereotype
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Override // Java override
    Optional<Course> findById(Long id); // Course could be optional
}
