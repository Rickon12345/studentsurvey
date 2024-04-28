package com.swinburne.studentsurvey.domain;

public class Participant {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String percEffort;
    private String attendance;
    private String percAcademic;
    private String completeYears;
    private String house;
    private String surveyDate;

    public Participant() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPercEffort() {
        return percEffort;
    }

    public void setPercEffort(String percEffort) {
        this.percEffort = percEffort;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getPercAcademic() {
        return percAcademic;
    }

    public void setPercAcademic(String percAcademic) {
        this.percAcademic = percAcademic;
    }

    public String getCompleteYears() {
        return completeYears;
    }

    public void setCompleteYears(String completeYears) {
        this.completeYears = completeYears;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
    }
}
