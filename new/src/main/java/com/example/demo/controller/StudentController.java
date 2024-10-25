package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("courses", courseService.findAll());
        return "students";
    }

    @PostMapping
    public String createStudent(@ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @PutMapping("/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student studentDetails) {
        studentService.findById(id).ifPresent(student -> {
            student.setName(studentDetails.getName());
            student.setAge(studentDetails.getAge());
            student.setCourse(studentDetails.getCourse());
            studentService.save(student);
        });
        return "redirect:/students";
    }
}
