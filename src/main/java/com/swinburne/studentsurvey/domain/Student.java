package com.swinburne.studentsurvey.domain;

import java.util.Objects;

public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String gender;
    private String email;
    private String status;
    private String registrationDate;
    private String dateOfBirth;
    public Student() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return Objects.equals(getFirstName(), student.getFirstName()) && Objects.equals(getLastName(), student.getLastName()) && Objects.equals(getPassword(), student.getPassword()) && Objects.equals(getGender(), student.getGender()) && Objects.equals(getEmail(), student.getEmail()) && Objects.equals(getStatus(), student.getStatus()) && Objects.equals(getRegistrationDate(), student.getRegistrationDate()) && Objects.equals(getDateOfBirth(), student.getDateOfBirth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getPassword(), getGender(), getEmail(), getStatus(), getRegistrationDate(), getDateOfBirth());
    }
}
