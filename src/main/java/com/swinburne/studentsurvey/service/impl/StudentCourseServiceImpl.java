package com.swinburne.studentsurvey.service.impl;

import com.swinburne.studentsurvey.dao.StudentCourseDao;
import com.swinburne.studentsurvey.domain.StudentCourse;
import com.swinburne.studentsurvey.service.StudentCourseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

	@Resource
	private StudentCourseDao studentCourseDao;
	
	@Override
	public List<StudentCourse> findAll(){
		return studentCourseDao.findAll();
	}

	@Override
	public List<StudentCourse> findStudentCourseByStudentId(Long studentId){
		return studentCourseDao.findStudentCourseByStudentId(studentId);
	}
	
	@Override
	public Integer save(StudentCourse tc) {
		return studentCourseDao.save(tc);
	};

	@Override
	public Integer deleteStudentCourseByStudentIdAndCourseId(Integer studentId, Integer courseId) {
		return studentCourseDao.deleteStudentCourseByStudentIdAndCourseId(studentId, courseId);
	};
}
