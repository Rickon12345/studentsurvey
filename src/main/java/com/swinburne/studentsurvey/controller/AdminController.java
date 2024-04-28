package com.swinburne.studentsurvey.controller;

import com.swinburne.studentsurvey.domain.Student;
import com.swinburne.studentsurvey.domain.User;
import com.swinburne.studentsurvey.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentClassService studentClassService;

    public AdminController() {
    }

    @GetMapping("/admin/login")
    public String getIndex(Model model) {
        model.addAttribute("user", new User());
        return "adminLogin";
    }

    @PostMapping("/admin/login")
    public String adminLogin(@ModelAttribute User user, Model model) {
//        User userInfo = this.userService.userLogin(user);
        if(user != null && "admin".equals(user.getEmail()) && "admin".equals(user.getUserPassword())){
            List<Student> studentList = this.studentService.students();
            model.addAttribute("students", studentList);
            return "studentList";
        }else{
            model.addAttribute("user", new User());
            return "adminLogin";
        }
    }

    @GetMapping("/teachers")
    public String teachers(Model model) {
        model.addAttribute("teachers",this.teacherService.teachers());
        return "teacherList";
    }

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students",this.studentService.students());
        return "studentList";
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("courses",this.courseService.courses());
        return "courseList";
    }

    @GetMapping("/classes")
    public String classes(Model model) {
        model.addAttribute("classes",this.classroomService.analysisClass());
        return "classList";
    }


    @GetMapping("/course/delete/{id}")
    public String courseDelete(@PathVariable("id") String id, Model model) {
        try {
            this.courseService.courseDelete(Long.valueOf(id));
        } catch (Exception var4) {
            model.addAttribute("courses",this.courseService.courses());
            return "courseList";
        }
        model.addAttribute("courses",this.courseService.courses());
        return "courseList";
    }
}
