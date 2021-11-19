package com.example.ist412se_v11_7.service;

import java.util.List; // Used for list of students
import java.util.Optional; // Optional used to contain null and/or not-null values
// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Page.html
import org.springframework.data.domain.Page; // Used to define findPaginated method return type
import com.example.ist412se_v11_7.model.Student; // Import student model class
import com.example.ist412se_v11_7.repository.StudentRepository; // Import student repository class
import org.springframework.beans.factory.annotation.Autowired; // Autowired annotations
import org.springframework.stereotype.Service; // Service annotations
// Used in findPaginated method
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() { return studentRepository.findAll();}

    @Override
    public void saveStudent(Student student) { this.studentRepository.save(student);}

    @Override
    public Student getStudentById(long sid) {
        Optional<Student> optional = studentRepository.findById(sid);
        Student student = null;
        if(optional.isPresent()) {
            student = optional.get();
        }
        else {
            throw new RuntimeException("Student not found for id: " + sid);
        }
        return student;
    }

    @Override
    public void deleteStudentById(long sid) { this.studentRepository.deleteById(sid);}

    @Override
    public Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.studentRepository.findAll(pageable);
    }
}
