package com.swinburne.studentsurvey.domain;

public class Response {
    private Long instanceId;
    private Long participantId;
    private String status;
    private String manbox1;
    private String manbox2;
    private String manbox3;
    private String manbox4;
    private String manbox5;
    private String isolated;
    private String womenDifferent;
    private String manbox5Overall;
    private String language;
    private String question5;
    private String masculinityContrained;
    private String growthMindset;
    private String COVID;
    private String criticises;
    private String menBetterSTEM;
    private String pwiWellbeing;
    private String intelligence1;
    private String intelligence2;
    private String soft;
    private String opinion;
    private String nerds;
    private String schoolSupportEngage;
    private String comfortable;
    private String future;
    private String bullying;
    private String candidatePercEffort;
    private String comments;
    private String surveyDate;

    private Participant participant;

    public Response() {
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManbox1() {
        return manbox1;
    }

    public void setManbox1(String manbox1) {
        this.manbox1 = manbox1;
    }

    public String getManbox2() {
        return manbox2;
    }

    public void setManbox2(String manbox2) {
        this.manbox2 = manbox2;
    }

    public String getManbox3() {
        return manbox3;
    }

    public void setManbox3(String manbox3) {
        this.manbox3 = manbox3;
    }

    public String getManbox4() {
        return manbox4;
    }

    public void setManbox4(String manbox4) {
        this.manbox4 = manbox4;
    }

    public String getManbox5() {
        return manbox5;
    }

    public void setManbox5(String manbox5) {
        this.manbox5 = manbox5;
    }

    public String getIsolated() {
        return isolated;
    }

    public void setIsolated(String isolated) {
        this.isolated = isolated;
    }

    public String getWomenDifferent() {
        return womenDifferent;
    }

    public void setWomenDifferent(String womenDifferent) {
        this.womenDifferent = womenDifferent;
    }

    public String getManbox5Overall() {
        return manbox5Overall;
    }

    public void setManbox5Overall(String manbox5Overall) {
        this.manbox5Overall = manbox5Overall;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getQuestion5() {
        return question5;
    }

    public void setQuestion5(String question5) {
        this.question5 = question5;
    }

    public String getMasculinityContrained() {
        return masculinityContrained;
    }

    public void setMasculinityContrained(String masculinityContrained) {
        this.masculinityContrained = masculinityContrained;
    }

    public String getGrowthMindset() {
        return growthMindset;
    }

    public void setGrowthMindset(String growthMindset) {
        this.growthMindset = growthMindset;
    }

    public String getCOVID() {
        return COVID;
    }

    public void setCOVID(String COVID) {
        this.COVID = COVID;
    }

    public String getCriticises() {
        return criticises;
    }

    public void setCriticises(String criticises) {
        this.criticises = criticises;
    }

    public String getMenBetterSTEM() {
        return menBetterSTEM;
    }

    public void setMenBetterSTEM(String menBetterSTEM) {
        this.menBetterSTEM = menBetterSTEM;
    }

    public String getPwiWellbeing() {
        return pwiWellbeing;
    }

    public void setPwiWellbeing(String pwiWellbeing) {
        this.pwiWellbeing = pwiWellbeing;
    }

    public String getIntelligence1() {
        return intelligence1;
    }

    public void setIntelligence1(String intelligence1) {
        this.intelligence1 = intelligence1;
    }

    public String getIntelligence2() {
        return intelligence2;
    }

    public void setIntelligence2(String intelligence2) {
        this.intelligence2 = intelligence2;
    }

    public String getSoft() {
        return soft;
    }

    public void setSoft(String soft) {
        this.soft = soft;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getNerds() {
        return nerds;
    }

    public void setNerds(String nerds) {
        this.nerds = nerds;
    }

    public String getSchoolSupportEngage() {
        return schoolSupportEngage;
    }

    public void setSchoolSupportEngage(String schoolSupportEngage) {
        this.schoolSupportEngage = schoolSupportEngage;
    }

    public String getComfortable() {
        return comfortable;
    }

    public void setComfortable(String comfortable) {
        this.comfortable = comfortable;
    }

    public String getFuture() {
        return future;
    }

    public void setFuture(String future) {
        this.future = future;
    }

    public String getBullying() {
        return bullying;
    }

    public void setBullying(String bullying) {
        this.bullying = bullying;
    }

    public String getCandidatePercEffort() {
        return candidatePercEffort;
    }

    public void setCandidatePercEffort(String candidatePercEffort) {
        this.candidatePercEffort = candidatePercEffort;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
    }
}
