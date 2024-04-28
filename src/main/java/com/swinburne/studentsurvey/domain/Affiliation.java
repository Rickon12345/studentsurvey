package com.swinburne.studentsurvey.domain;

public class Affiliation {
    private Long id;
    private String title;
    private String category;
    private String description;
    private String nominationwave;
    private String addtional1;
    private String addtional2;

    public Affiliation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNominationwave() {
        return nominationwave;
    }

    public void setNominationwave(String nominationwave) {
        this.nominationwave = nominationwave;
    }

    public String getAddtional1() {
        return addtional1;
    }

    public void setAddtional1(String addtional1) {
        this.addtional1 = addtional1;
    }

    public String getAddtional2() {
        return addtional2;
    }

    public void setAddtional2(String addtional2) {
        this.addtional2 = addtional2;
    }
}
