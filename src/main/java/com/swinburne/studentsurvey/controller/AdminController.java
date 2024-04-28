package com.swinburne.studentsurvey.controller;

import com.swinburne.studentsurvey.domain.*;
import com.swinburne.studentsurvey.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Autowired
    private AffiliationService affiliationService;
    @Autowired
    private NodeAnalyticsService nodeAnalyticsService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private InsightService insightService;


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
//            List<Student> studentList = this.studentService.students();
//            model.addAttribute("students", studentList);
            List<AffiliationGroup> affiliationGroupList = this.affiliationService.findAffiliationGroup("2023-01-01 00:00:00");
            model.addAttribute("affiliationGroups", affiliationGroupList);
            Participant participant = new Participant();
            participant.setSurveyDate("2023-01-01 00:00:00");
            model.addAttribute("participant", participant);
            model.addAttribute("participantList", this.participantService.findAllBySemester("2023-01-01 00:00:00"));
            List<Top10> tlist = new ArrayList<>();
            List<NodeAnalytics> list = this.nodeAnalyticsService.findTopBySurveyDateAndType(participant.getSurveyDate(), "Clustering");
            list.forEach(i->{
                Top10 t = new Top10();
                t.setName(i.getParticipant().getFirstName()+" "+i.getParticipant().getLastName());
                t.setValue(i.getClustering());
                tlist.add(t);
            });
            model.addAttribute("topList", tlist);
            model.addAttribute("insightList", this.insightService.findInsightBySemester(participant.getSurveyDate()));
            return "schoolDashboard";
        }else{
            model.addAttribute("user", new User());
            return "adminLogin";
        }
    }

    @PostMapping("/admin/search")
    public String adminSearch(@ModelAttribute Participant participant, Model model) {
        List<AffiliationGroup> affiliationGroupList = this.affiliationService.findAffiliationGroup(participant.getSurveyDate());
        model.addAttribute("affiliationGroups", affiliationGroupList);
        model.addAttribute("participant", participant);
        model.addAttribute("participantList", this.participantService.findAllBySemester(participant.getSurveyDate()));
        List<Top10> tlist = new ArrayList<>();
        List<NodeAnalytics> list = this.nodeAnalyticsService.findTopBySurveyDateAndType(participant.getSurveyDate(), "Clustering");
        list.forEach(i->{
            Top10 t = new Top10();
            t.setName(i.getParticipant().getFirstName()+" "+i.getParticipant().getLastName());
            t.setValue(i.getClustering());
            tlist.add(t);
            });
        model.addAttribute("topList", tlist);
        model.addAttribute("insightList", this.insightService.findInsightBySemester(participant.getSurveyDate()));
        return "schoolDashboard";
    }

    @GetMapping("/admin/top/{topType}/semester/{surveyDate}")
    public String adminTop(@PathVariable("topType") String topType, @PathVariable("surveyDate") String surveyDate, Model model) {
        List<NodeAnalytics> list = this.nodeAnalyticsService.findTopBySurveyDateAndType(surveyDate, topType);
        List<Top10> tlist = new ArrayList<>();
        list.forEach(i->{
            Top10 t = new Top10();
            t.setName(i.getParticipant().getFirstName()+" "+i.getParticipant().getLastName());
            switch (topType) {
                case "betweenness_centrality" -> t.setValue(i.getBetweenness());
                case "Clustering" -> t.setValue(i.getClustering());
                case "in_degree_centrality" -> t.setValue(i.getInDegree());
                default -> t.setValue(i.getOutDegree());
            }
            tlist.add(t);
        });
        model.addAttribute("nodeAnalytics", this.nodeAnalyticsService.findTopBySurveyDateAndType(surveyDate, topType));
        model.addAttribute("topList", tlist);

        return "schoolDashboard::top10";
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
