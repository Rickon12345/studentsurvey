package com.swinburne.studentsurvey.domain;

import java.util.List;

public class Influential {
    private Long participantId;
    private Participant participant;
    private Long target;
    private String surveyDate;
    private List<Participant> tolist;
    private List<Participant> fromlist;
    public Influential() {
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
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

    public List<Participant> getTolist() {
        return tolist;
    }

    public void setTolist(List<Participant> tolist) {
        this.tolist = tolist;
    }

    public List<Participant> getFromlist() {
        return fromlist;
    }

    public void setFromlist(List<Participant> fromlist) {
        this.fromlist = fromlist;
    }
}
