package com.swinburne.studentsurvey.domain;

import java.util.List;

public class Hierarchy {
    private String name;
    private String value;
    private List<Hierarchy> children;
    public Hierarchy() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Hierarchy> getChildren() {
        return children;
    }

    public void setChildren(List<Hierarchy> children) {
        this.children = children;
    }
}
