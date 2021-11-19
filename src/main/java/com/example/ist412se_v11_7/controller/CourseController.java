package com.example.ist412se_v11_7.controller; // Package declaration

// Local project imports
import com.example.ist412se_v11_7.model.Course; // Course model class
import com.example.ist412se_v11_7.service.CourseService; // Course service class
// Spring imports
import org.springframework.beans.factory.annotation.Autowired; // Autowired annotations
// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Page.html
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller; // Controller annotation
import org.springframework.ui.Model; // Used as parameter for viewHomePage method
import org.springframework.web.bind.annotation.*; // GetMapping, PostMapping, ModelAttribute annotations - saveCourse
//import org.springframework.web.bind.annotation.GetMapping; // GetMapping annotations
//import org.springframework.web.bind.annotation.ModelAttribute; // ModelAttribute annotations
//import org.springframework.web.bind.annotation.PostMapping; // PostMapping annotations

// MISC imports
import java.util.List;


@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listCourses", courseService.getAllCourses());
        return "index";
    }

    @RequestMapping(value="/HelloWorld")
    public String helloWorld(Model model) { return "HelloWorld";}

    @GetMapping("/showNewCourseForm") // Map to HTML page in resources/templates folder
    public String showNewCourseForm(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        return  "new_course";
    }
    @PostMapping("/saveCourse") // Map to HTML page in resources/templates folder after course is saved
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/";
    }
    @GetMapping("/showFormForUpdate/{id}") // Map to HTML page in resources/templates folder
    public String showFormForUpdate(@PathVariable(value="id") long id, Model model) {
        Course course = courseService.getCourseById(id);

        model.addAttribute("course", course);
        return "update_course";
    }

    @GetMapping("deleteCourse/{id}")
    public String deleteCourse(@PathVariable (value = "id") long id) {
        // Method to delete course is called
        this.courseService.deleteCourseById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                               @RequestParam("sortField") String sortField,
                               @RequestParam("sortDir") String sortDir,
                               Model model) {
        int pageSize = 5;
        Page<Course> page = courseService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Course> listCourses = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listCourses", listCourses);
        return "index";
    }
}
