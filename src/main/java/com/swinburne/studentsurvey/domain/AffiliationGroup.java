package com.swinburne.studentsurvey.domain;

public class AffiliationGroup {
    private String affiliationTitle;
    private String count;
    private String surveyDate;

    public AffiliationGroup() {
    }

    public String getAffiliationTitle() {
        return affiliationTitle;
    }

    public void setAffiliationTitle(String affiliationTitle) {
        this.affiliationTitle = affiliationTitle;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
    }
}
