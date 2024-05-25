package com.swinburne.studentsurvey.controller;

import com.swinburne.studentsurvey.domain.*;
import com.swinburne.studentsurvey.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
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

    public StudentController() {
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        session = null;
        return "homepage";
    }

    @GetMapping("/student/login")
    public String studentLogin(Model model) {
        session = null;
        model.addAttribute("student", new Student());
        return "studentLogin";
    }


    @PostMapping("/student/login")
    public String studentLogin(@ModelAttribute Student student, Model model) {
        Student studentInfo = this.studentService.studentLogin(student);
        if(studentInfo != null){
            session = request.getSession();
            session.setAttribute("studentId",studentInfo.getId());
            model.addAttribute("student", studentInfo);
            Participant participant = this.participantService.findByStudentIdAndSemester("2023-01-01 00:00:00", studentInfo.getId());
            model.addAttribute("participant", participant);
            model.addAttribute("nodeAnalytics", this.nodeAnalyticsService.findNodeAnalyticsByParticipantId(participant.getId()));
            model.addAttribute("friends", this.participantService.findFriendByParticipantId(participant));
            model.addAttribute("influential", this.participantService.findInfluentialByParticipantId(participant));
            model.addAttribute("disrespect", this.participantService.findDisrespectByParticipantId(participant));
            model.addAttribute("feedback", this.participantService.findFeedbackByParticipantId(participant));
            model.addAttribute("moretime", this.participantService.findMoreTimeByParticipantId(participant));
            model.addAttribute("advice", this.participantService.findAdviceByParticipantId(participant));
            model.addAttribute("response",this.responseService.findByStudentIdAndSemester("2023-01-01 00:00:00", studentInfo.getId()));
            model.addAttribute("classAvg",this.responseService.findClassAvg("2023-01-01 00:00:00", participant.getHouse()));
            model.addAttribute("schoolAvg",this.responseService.findSchoolAvg("2023-01-01 00:00:00"));

            return "studentDashboard";
        }else{
            model.addAttribute("student", new Student());
            return "studentLogin";
        }
    }

    @PostMapping("/student/search")
    public String studentSearch(@ModelAttribute Participant participant, Model model) {
        session = request.getSession();
        Long studentId = (Long) session.getAttribute("studentId");
        Participant participant1 = this.participantService.findByStudentIdAndSemester(participant.getSurveyDate(), studentId);
        model.addAttribute("participant", participant1);
        model.addAttribute("nodeAnalytics", this.nodeAnalyticsService.findNodeAnalyticsByParticipantId(participant1.getId()));
        model.addAttribute("friends", this.participantService.findFriendByParticipantId(participant1));
        model.addAttribute("influential", this.participantService.findInfluentialByParticipantId(participant1));
        model.addAttribute("disrespect", this.participantService.findDisrespectByParticipantId(participant1));
        model.addAttribute("feedback", this.participantService.findFeedbackByParticipantId(participant1));
        model.addAttribute("moretime", this.participantService.findMoreTimeByParticipantId(participant1));
        model.addAttribute("advice", this.participantService.findAdviceByParticipantId(participant1));
        model.addAttribute("response",this.responseService.findByStudentIdAndSemester(participant1.getSurveyDate(), studentId));
        model.addAttribute("classAvg",this.responseService.findClassAvg(participant1.getSurveyDate(), participant1.getHouse()));
        model.addAttribute("schoolAvg",this.responseService.findSchoolAvg(participant1.getSurveyDate()));
        return "studentDashboard";
    }

    @GetMapping("/student/register")
    public String register(Model model) {
        model.addAttribute("student", new Student());
        return "studentCreate";
    }

    @GetMapping("/student/{id}")
    public String getStudentById(@PathVariable("id") String id, Model model) {
        model.addAttribute("data",this.studentService.getStudentById(Long.valueOf(id)));
        return "studentInfo";
    }

    @RequestMapping(
        path = {"/student/delete/{id}"},
        method = {RequestMethod.GET},
        produces = {"application/json"}
    )
    public ResponseEntity<Event> studentDelete(@PathVariable("id") String id) {
        Event event = new Event();

        try {
            this.studentService.studentDelete(Long.valueOf(id));
        } catch (Exception var4) {
            event.setResult("delete student have error." + var4);
            return ResponseEntity.ok(event);
        }

        event.setResult("result: student Successfully removed.");
        return ResponseEntity.ok(event);
    }
}
