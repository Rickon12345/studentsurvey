package com.swinburne.studentsurvey.domain;

import java.util.ArrayList;
import java.util.List;

public class Friends {
    private Long participantId;
    private Participant participant;
    private Long target;
    private String surveyDate;
    //private List<Friends> fl;
    private List<Participant> list;
    public Friends() {
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public List<Participant> getList() {
        return list;
    }

    public void setList(List<Participant> list) {
        this.list = list;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public String getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
    }
}