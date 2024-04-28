package com.swinburne.studentsurvey.controller;

import com.swinburne.studentsurvey.domain.Course;
import com.swinburne.studentsurvey.service.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    public CourseController() {
    }

    @GetMapping("/course/create")
    public String courseCreate(Model model) {
        model.addAttribute("course", new Course());
        return "courseCreate";
    }

    @PostMapping("/course/save")
    public String courseSave(HttpServletRequest request, @ModelAttribute Course course, Model model) {
        this.courseService.courseSave(course);
        model.addAttribute("courses",this.courseService.courses());
        return "courseList";
    }


}
