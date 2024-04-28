package com.swinburne.studentsurvey.service.impl;

import com.swinburne.studentsurvey.dao.TeacherDao;
import com.swinburne.studentsurvey.domain.Teacher;
import com.swinburne.studentsurvey.service.TeacherService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherDao teacherDao;

    public TeacherServiceImpl() {
    }

    public List<Teacher> teachers() {
        return this.teacherDao.findAll();
    }

    public Integer teacherSave(Teacher teacher) {
        teacher.setStatus("Y");
        return this.teacherDao.save(teacher);
    }

    public Teacher teacherLogin(Teacher teacher) {
        return this.teacherDao.teacherLogin(teacher);
    }

    public Teacher getTeacherById(Long id) {
        return this.teacherDao.findById(id);
    }

    public void teacherDelete(Long id) {
        this.teacherDao.deleteById(id);
    }
}
