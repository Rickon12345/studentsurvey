package com.swinburne.studentsurvey.controller;

import com.swinburne.studentsurvey.domain.*;
import com.swinburne.studentsurvey.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClassroomController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentCourseService studentCourseService;
    @Autowired
    private StudentClassService studentClassService;
    @Autowired
    private TeacherCourseService teacherCourseService;
    @Autowired
    private CourseService courseService;

    @Autowired
    private ClassroomService classroomService;
    @Autowired
    HttpServletRequest request;

    HttpSession session = null;

    public ClassroomController() {
    }

    @GetMapping("/student/class/join/{classId}")
    public String studentClassJoin(@PathVariable("classId") String classId, Model model) {
        session = request.getSession();
        Long studentId = (Long) session.getAttribute("studentId");
        List<Student> studentList = (List<Student>) session.getAttribute("studentList");
        Student s = studentService.getStudentById(studentId);
        List<StudentClass> scList = studentClassService.findStudentClassByStudentIdAndClassId(studentId,Long.valueOf(classId));
        for(StudentClass sc : scList){
            sc.setStatus("Attended");
            studentClassService.save(sc);
        }
        if(studentList==null){
            studentList = new ArrayList<Student>();
            studentList.add(s);
        }else{
            if(!studentList.contains(s)){
                studentList.add(s);
            }
        }

        session.setAttribute("studentList",studentList);
        model.addAttribute("messageList", this.classroomService.findClassroomByClassId(Long.valueOf(classId)));
        model.addAttribute("student", s);
        model.addAttribute("studentList", studentList);
        Classroom c = new Classroom();
        c.setStudentId(studentId);
        c.setTeacherCourseId(Long.valueOf(classId));
        model.addAttribute("classroom", c);
        return "classroom";
    }

    @GetMapping("/teacher/class/join/{classId}")
    public String teacherClassJoin(@PathVariable("classId") String classId, Model model) {
        session = request.getSession();
        Long teacherId = (Long) session.getAttribute("teacherId");
        List<Student> studentList = (List<Student>) session.getAttribute("studentList");
        if(studentList==null){
            studentList = new ArrayList<Student>();
        }
        model.addAttribute("messageList", this.classroomService.findClassroomByClassId(Long.valueOf(classId)));
        model.addAttribute("teacher", teacherService.getTeacherById(teacherId));
        model.addAttribute("studentList", studentList);
        Classroom c = new Classroom();
        c.setTeacherId(teacherId);
        c.setTeacherCourseId(Long.valueOf(classId));
        model.addAttribute("classroom", c);
        return "teacherClassroom";
    }

    @PostMapping("/student/message/save")
    public String studentMessageSave(@ModelAttribute Classroom classroom, Model model) {
        classroom.setMessageType("Question");
        classroom.setTeacherId(0L);
        this.classroomService.save(classroom);
        model.addAttribute("messageList", this.classroomService.findClassroomByClassId(classroom.getTeacherCourseId()));
        return "classroom::refresh";
    }

    @GetMapping("/message/refresh/{classId}")
    public String studentMessageRefresh(@PathVariable("classId") String classId, Model model) {
        model.addAttribute("messageList", this.classroomService.findClassroomByClassId(Long.valueOf(classId)));
        return "classroom::refresh";
    }

    @GetMapping("/message/attend/{classId}")
    public String messageAttend(@PathVariable("classId") String classId, Model model) {
        session = request.getSession();
        List<Student> studentList = (List<Student>) session.getAttribute("studentList");
        model.addAttribute("studentList", studentList);
        return "classroom::attend";
    }

    @PostMapping("/teacher/message/save")
    public String teacherMessageSave(@ModelAttribute Classroom classroom, Model model) {
        classroom.setMessageType("Answer");
        classroom.setStudentId(0L);
        this.classroomService.save(classroom);
        model.addAttribute("messageList", this.classroomService.findClassroomByClassId(classroom.getTeacherCourseId()));
        return "teacherClassroom::refresh";
    }

}
