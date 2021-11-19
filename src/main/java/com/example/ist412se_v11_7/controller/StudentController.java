package com.example.ist412se_v11_7.controller; // Package declaration

// Local project imports
import com.example.ist412se_v11_7.model.Course; // Course model class
import com.example.ist412se_v11_7.model.Student; // Student model class
import com.example.ist412se_v11_7.repository.CourseRepository; // Course repository class
import com.example.ist412se_v11_7.repository.StudentRepository; // Student repository class
import com.example.ist412se_v11_7.service.CourseService; // Course service class
import com.example.ist412se_v11_7.service.StudentService; // Student service class
// Spring imports
import org.springframework.beans.factory.annotation.Autowired; // Autowired annotations
import org.springframework.stereotype.Controller; // Controller annotation
import org.springframework.ui.Model; // Used as parameter for viewHomePage method
import org.springframework.web.bind.annotation.*; // GetMapping, PostMapping, ModelAttribute annotations - saveStudent
// MISC imports
import java.util.List;
import java.util.Optional; // Optional used to contain null and/or not-null values
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/studentList") // Map to HTML page in resources/templates folder
    public String viewStudentPage(Model model) {
        model.addAttribute("listStudents", studentService.getAllStudents());
        return "student_list";
    }
    @GetMapping("/showNewStudentForm") // Map to HTML page in resources/templates folder
    public String showNewStudentForm(Model model) {
        Student student = new Student();
        // Create model attribute to bind form data
        model.addAttribute("student", student);
        return "new_student";
    }
    @PostMapping("/saveStudent")// Map to HTML page in resources/templates folder after student is saved
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student); // Save student data to database
        return "redirect:/studentList";
    }

    @RequestMapping(value="/get/{sid]")
    public String getStudentId(@PathVariable("sid") Long sid, Model model) {
        Optional<Student> findStudentId = studentRepository.findById(sid);

        model.addAttribute("title", "Data Student");
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("student", findStudentId);

        log.info("getStudentId() : " + sid);
        return "redirect:/studentList";
    }

    @GetMapping("/showStudFormForUpdate/{sid}")
    public String showFormForUpdate(@PathVariable(value="sid") long sid, Model model) {
        Student student = studentService.getStudentById(sid); // Get student from service
        List<Course> allCourses = courseService.getAllCourses();
        model.addAttribute("allCourses", allCourses); // Set allCourses as model attribute
        model.addAttribute("student", student); // Set student as model attribute to populate the form
        return "update_student";
    }

    @GetMapping("/deleteStudent/{sid}")
    public String deleteStudent(@PathVariable (value = "sid") long sid) {
        this.studentService.deleteStudentById(sid);
        return "redirect:/studentList";
    }

}
