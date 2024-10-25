package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "courses";
    }

    @PostMapping
    public String createCourse(@ModelAttribute Course course) {
        courseService.save(course);
        return "redirect:/courses";
    }

    @PutMapping("/{id}")
    public String updateCourse(@PathVariable Long id, @ModelAttribute Course courseDetails) {
        courseService.findById(id).ifPresent(course -> {
            course.setName(courseDetails.getName());
            courseService.save(course);
        });
        return "redirect:/courses";
    }
}
