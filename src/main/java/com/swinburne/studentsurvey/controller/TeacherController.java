package com.swinburne.studentsurvey.controller;

import com.swinburne.studentsurvey.domain.*;
import com.swinburne.studentsurvey.service.*;
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
    private StudentService studentService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private ResponseService responseService;
    @Autowired
    private NodeAnalyticsService nodeAnalyticsService;
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
            Participant participant = new Participant();
            model.addAttribute("participant", participant);
            Response response = new Response();
            model.addAttribute("response",response);
            NodeAnalytics nodeAnalytics = new NodeAnalytics();
            model.addAttribute("nodeAnalytics",nodeAnalytics);
            return "teacherDashboard";
        }else{
            model.addAttribute("teacher", new Teacher());
            return "teacherLogin";
        }
    }

    @PostMapping("/teacher/search")
    public String studentSearch(@ModelAttribute Participant participant, Model model) {
        Participant participant1 = this.participantService.findByParticipantId(participant.getId());
        model.addAttribute("participant", participant1);
        model.addAttribute("nodeAnalytics", this.nodeAnalyticsService.findNodeAnalyticsByParticipantId(participant1.getId()));
        model.addAttribute("friends", this.participantService.findFriendByParticipantId(participant1));
        model.addAttribute("influential", this.participantService.findInfluentialByParticipantId(participant1));
        model.addAttribute("disrespect", this.participantService.findDisrespectByParticipantId(participant1));
        model.addAttribute("feedback", this.participantService.findFeedbackByParticipantId(participant1));
        model.addAttribute("moretime", this.participantService.findMoreTimeByParticipantId(participant1));
        model.addAttribute("advice", this.participantService.findAdviceByParticipantId(participant1));
        model.addAttribute("response",this.responseService.findByParticipantId(participant.getId()));
        model.addAttribute("classAvg",this.responseService.findClassAvg(participant1.getSurveyDate(), participant1.getHouse()));
        model.addAttribute("schoolAvg",this.responseService.findSchoolAvg(participant1.getSurveyDate()));
        return "teacherDashboard";
    }

    @GetMapping("/teacher/register")
    public String register(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacherCreate";
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
