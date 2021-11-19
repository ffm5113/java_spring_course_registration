package com.example.ist412se_v11_7.model; // Package declaration

// MISC imports
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

// javax.persistence entity and table annotations
@Entity // Create database entity
@Table(name="student") // Create student table in MySQL
public class Student implements Serializable{
    // Student class attributes
    @Id // MySQL table ID field using student ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sid; // Student ID
    @Column(name = "stud_name")
    private String studName;
    @Column(name = "stud_email")
    private String studEmail;

    @ManyToMany(cascade = CascadeType.MERGE)
    // Intersection table to handle student_course n:m relationship
    @JoinTable(name = "student_course",
            joinColumns = {@JoinColumn(name = "sid")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    // HashSet object instantiation with set of Course objects
    public Set<Course> courses = new HashSet<>();

    public long getSid() { return sid;}
    public void setSid(long sid) { this.sid = sid;}

    public String getStudName() { return studName;}
    public void setStudName(String studName) { this.studName = studName;}

    public String getStudEmail() { return studEmail;}
    public void setStudEmail(String studEmail) { this.studEmail = studEmail;}

    public Set<Course> getCourses() { return courses;}
    public void setCourses(Set<Course> courses) { this.courses = courses;}
}
