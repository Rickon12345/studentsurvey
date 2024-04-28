package com.swinburne.studentsurvey.domain;

public class Moretime {
    private Long participantId;
    private Long target;
    private String surveyDate;
    private Participant participant;
    private Participant participantT;
    public Moretime() {
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Participant getParticipantT() {
        return participantT;
    }

    public void setParticipantT(Participant participantT) {
        this.participantT = participantT;
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
