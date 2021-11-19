package com.example.ist412se_v11_7.model; // Package declaration

// MISC imports
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

// javax.persistence entity and table annotations
@Entity // Create database entity
@Table(name="courses") // Create courses table in MySQL
public class Course implements Serializable {
    // Course class attributes
    @Id // MySQL table ID field
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Course ID
    @Column(name = "dept") // MySQL table column
    private String courseDept;
    @Column(name = "num") // MySQL table column
    private String courseNum;
    @Column(name = "name") // MySQL table column
    private String courseName;
    @Column(name = "num_studs") // MySQL table column
    private String courseStuds;

    @ManyToMany(mappedBy="courses") // Student can enroll in many courses. Course can have many students
    @JsonIgnore
    // HashSet object instantiation with set of Student objects
    private Set<Student> students = new HashSet<>();

    public long getId() {return id;}
    public void setId(long id) { this.id = id;}

    public String getCourseDept() { return courseDept;}
    public void setCourseDept(String courseDept) { this.courseDept = courseDept;}

    public String getCourseNum() { return courseNum;}
    public void setCourseNum(String courseNum) { this.courseNum = courseNum;}

    public String getCourseName() { return courseName;}
    public void setCourseName(String courseName) { this.courseName = courseName;}

    public String getCourseStuds() { return courseStuds;}
    public void setCourseStuds(String courseStuds) { this.courseStuds = courseStuds;}

    public Set<Student> getStudents() { return students;}
    public void setStudents(Set<Student> students) { this.students = students;}
}