package com.swinburne.studentsurvey.service.impl;

import java.util.List;


import com.swinburne.studentsurvey.dao.TeacherCourseDao;
import com.swinburne.studentsurvey.domain.TeacherCourse;
import com.swinburne.studentsurvey.service.TeacherCourseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {

	@Resource
	private TeacherCourseDao teacherCourseDao;
	
	@Override
	public List<TeacherCourse> findAll(){
		return teacherCourseDao.findAll();
	}

	@Override
	public List<TeacherCourse> findTeacherCourseByTeacherId(Long teacherId){
		return teacherCourseDao.findTeacherCourseByTeacherId(teacherId);
	}

	@Override
	public List<TeacherCourse> findTeacherCourseByCourseId(Long courseId){
		return teacherCourseDao.findTeacherCourseByCourseId(courseId);
	}

	@Override
	public Integer save(TeacherCourse tc) {
		return teacherCourseDao.save(tc);
	};

	@Override
	public Integer deleteTeacherCourseByTeacherIdAndCourseId(Integer teacherId, Integer courseId) {
		return teacherCourseDao.deleteTeacherCourseByTeacherIdAndCourseId(teacherId, courseId);
	};
}
