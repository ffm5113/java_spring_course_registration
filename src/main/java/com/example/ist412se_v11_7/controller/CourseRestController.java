package com.example.ist412se_v11_7.controller; // Package declaration

// Local project imports
import com.example.ist412se_v11_7.model.Course;
import com.example.ist412se_v11_7.service.CourseService;
// Spring imports
import org.springframework.beans.factory.annotation.Autowired; // Autowired annotations
import org.springframework.http.HttpStatus; // HttpStatus used in getAllCourses method
import org.springframework.http.ResponseEntity; // Used in getAllCourses method
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController; // Rest Controller annotations
import org.springframework.web.bind.annotation.RequestMapping; // RequestMapping annotations
// MISC imports
import java.util.List;

@RestController
@RequestMapping("/")
public class CourseRestController {
    @Autowired
    CourseService courseService;

    @RequestMapping(value = "/course/", method = RequestMethod.GET)
    // ResponseEntity to get courses in JSON format as response to REST request
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        if(courses.isEmpty()) {
            return new ResponseEntity<List<Course>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
    }
}
