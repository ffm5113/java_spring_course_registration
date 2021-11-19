package com.example.ist412se_v11_7.repository; // Package declaration

import com.example.ist412se_v11_7.model.Student; // Import student model class
import org.springframework.data.jpa.repository.JpaRepository; // For StudentRepository class to extend JpaRepository
import org.springframework.stereotype.Repository; // Used in the creation of a course repository

@Repository // Create course repository via Spring framework stereotype
public interface StudentRepository extends JpaRepository<Student, Long> {
}
