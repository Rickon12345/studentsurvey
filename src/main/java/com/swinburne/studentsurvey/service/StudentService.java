package com.swinburne.studentsurvey.service;

import com.swinburne.studentsurvey.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<Student> students();

    Integer studentSave(Student var1);

    Student studentLogin(Student var1);

    void studentDelete(Long var1);

    Student getStudentById(Long var1);
}
