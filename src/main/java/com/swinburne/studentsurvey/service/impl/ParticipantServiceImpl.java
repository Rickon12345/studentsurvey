package com.swinburne.studentsurvey.service.impl;

import com.swinburne.studentsurvey.dao.ParticipantDao;
import com.swinburne.studentsurvey.dao.StudentDao;
import com.swinburne.studentsurvey.domain.*;
import com.swinburne.studentsurvey.service.ParticipantService;
import com.swinburne.studentsurvey.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {
    @Resource
    private ParticipantDao participantDao;

    public ParticipantServiceImpl() {
    }

    public List<Participant> findByStudentId(Long id) {
        return this.participantDao.findByStudentId(id);
    }

    public List<Participant> findAllBySemester(String surveyDate) {
        return this.participantDao.findAllBySemester(surveyDate);
    }

    public Participant findByStudentIdAndSemester(String surveyDate,Long id){
        return this.participantDao.findByStudentIdAndSemester(surveyDate,id);
    }

    public Participant findByParticipantId(Long id){
        return this.participantDao.findByParticipantId(id);
    }

    public Friends findFriendByParticipantId(Participant p){
        Friends f = new Friends();
        f.setParticipantId(p.getId());
        f.setParticipant(p);
        List<Participant> fromParticipants = this.participantDao.findFromFriendByParticipantId(p.getId());
        List<Participant> toParticipants = this.participantDao.findToFriendByParticipantId(p.getId());
        f.setFromlist(fromParticipants);
        f.setTolist(toParticipants);
        return f;
    }

    public Influential findInfluentialByParticipantId(Participant p){
        Influential f = new Influential();
        f.setParticipantId(p.getId());
        f.setParticipant(p);
        List<Participant> fromParticipants = this.participantDao.findFromInfluentialByParticipantId(p.getId());
        List<Participant> toParticipants = this.participantDao.findFromInfluentialByParticipantId(p.getId());
        f.setFromlist(fromParticipants);
        f.setTolist(toParticipants);
        return f;
    }

    public Feedback findFeedbackByParticipantId(Participant p){
        Feedback f = new Feedback();
        f.setParticipantId(p.getId());
        f.setParticipant(p);
        List<Participant> fromParticipants = this.participantDao.findFromFeedbackByParticipantId(p.getId());
        List<Participant> toParticipants = this.participantDao.findToFeedbackByParticipantId(p.getId());
        f.setFromlist(fromParticipants);
        f.setTolist(toParticipants);
        return f;
    }

    public Disrespect findDisrespectByParticipantId(Participant p){
        Disrespect f = new Disrespect();
        f.setParticipantId(p.getId());
        f.setParticipant(p);
        List<Participant> fromParticipants = this.participantDao.findFromDisrespectByParticipantId(p.getId());
        List<Participant> toParticipants = this.participantDao.findToDisrespectByParticipantId(p.getId());
        f.setFromlist(fromParticipants);
        f.setTolist(toParticipants);
        return f;
    }

    public Advice findAdviceByParticipantId(Participant p){
        Advice f = new Advice();
        f.setParticipantId(p.getId());
        f.setParticipant(p);
        List<Participant> fromParticipants = this.participantDao.findFromAdviceByParticipantId(p.getId());
        List<Participant> toParticipants = this.participantDao.findToAdviceByParticipantId(p.getId());
        f.setFromlist(fromParticipants);
        f.setTolist(toParticipants);
        return f;
    }

    public Moretime findMoreTimeByParticipantId(Participant p){
        Moretime f = new Moretime();
        f.setParticipantId(p.getId());
        f.setParticipant(p);
        List<Participant> fromParticipants = this.participantDao.findFromMoreTimeByParticipantId(p.getId());
        List<Participant> toParticipants = this.participantDao.findToMoreTimeByParticipantId(p.getId());
        f.setFromlist(fromParticipants);
        f.setTolist(toParticipants);
        return f;
    }
}
