package com.swinburne.studentsurvey.controller;

import com.swinburne.studentsurvey.domain.Event;
import com.swinburne.studentsurvey.domain.Teacher;
import com.swinburne.studentsurvey.domain.TeacherCourse;
import com.swinburne.studentsurvey.service.CourseService;
import com.swinburne.studentsurvey.service.TeacherCourseService;
import com.swinburne.studentsurvey.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherCourseService teacherCourseService;
    @Autowired
    private CourseService courseService;
    @Autowired
    HttpServletRequest request;

    HttpSession session = null;

    public TeacherController() {
    }

    @GetMapping("/teacher/login")
    public String getIndex(Model model) {
        session = null;
        model.addAttribute("teacher", new Teacher());
        return "teacherLogin";
    }

    @PostMapping("/teacher/login")
    public String teacherLogin(HttpServletRequest request, @ModelAttribute Teacher teacher, Model model) {
        Teacher teacherInfo = this.teacherService.teacherLogin(teacher);
        if(teacherInfo != null){
            session = request.getSession();
            session.setAttribute("teacherId",teacherInfo.getId());
            model.addAttribute("teacher", teacherInfo);
            List<TeacherCourse> list = this.teacherCourseService.findTeacherCourseByTeacherId(teacherInfo.getId());
            model.addAttribute("teacherCourses", list);
            return "teacherInfo";
        }else{
            model.addAttribute("teacher", new Teacher());
            return "teacherLogin";
        }
    }

    @GetMapping("/teacher/register")
    public String register(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacherCreate";
    }

    @GetMapping("/teacher/course")
    public String teacherCourseCreate(Model model) {
        Long id = (Long) request.getSession().getAttribute("teacherId");
        model.addAttribute("teacher", this.teacherService.getTeacherById(id));
        model.addAttribute("courses", courseService.courses());
        TeacherCourse tc = new TeacherCourse();
        tc.setTeacherId(id);
        model.addAttribute("teacherCourse", tc);
        return "teacherCourseCreate";
    }

    @PostMapping("/teacher/course/save")
    public String teacherCourseSave(@ModelAttribute TeacherCourse teacherCourse, Model model) {
        this.teacherCourseService.save(teacherCourse);
        Teacher t = this.teacherService.getTeacherById(teacherCourse.getTeacherId());
        List<TeacherCourse> list = this.teacherCourseService.findTeacherCourseByTeacherId(teacherCourse.getTeacherId());
        model.addAttribute("teacher", t);
        model.addAttribute("teacherCourses", list);
        return "teacherInfo";
    }


    @GetMapping("/teacher/{id}")
    public String getTeacherById(@PathVariable("id") String id, Model model) {
        model.addAttribute("data",this.teacherService.getTeacherById(Long.valueOf(id)));
        return "teacherInfo";
    }

    @PostMapping("/teacher/save")
    public String teacherSave(HttpServletRequest request, @ModelAttribute Teacher teacher, Model model) {
        session = request.getSession();
        session.setAttribute("teacherId",teacher.getId());
        model.addAttribute("data",this.teacherService.teacherSave(teacher));
        return "teacherInfo";
    }

    @GetMapping("/teacher/delete/{id}")
    public String teacherDelete(@PathVariable("id") String id, RedirectAttributes attributes) {
        Event event = new Event();
        try {
            this.teacherService.teacherDelete(Long.valueOf(id));
        } catch (Exception var4) {
            attributes.addAttribute("message","delete teacher have error." + var4);
            return "redirect:/";
        }
        attributes.addAttribute("message","result: teacher Successfully removed.");
        return "redirect:/";
    }



}
