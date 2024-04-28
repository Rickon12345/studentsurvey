package com.swinburne.studentsurvey.service.impl;

import com.swinburne.studentsurvey.dao.StudentDao;
import com.swinburne.studentsurvey.domain.Student;
import com.swinburne.studentsurvey.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDao studentDao;

    public StudentServiceImpl() {
    }

    public List<Student> students() {
        return this.studentDao.findAll();
    }

    public Integer studentSave(Student student) {
        student.setStatus("Y");
        return this.studentDao.save(student);
    }

    public Student studentLogin(Student student) {
        return this.studentDao.studentLogin(student);
    }

    public Student getStudentById(Long id) {
        return this.studentDao.findById(id);
    }

    public void studentDelete(Long id) {
        this.studentDao.deleteById(id);
    }
}
