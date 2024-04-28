package com.swinburne.studentsurvey.service.impl;

import com.swinburne.studentsurvey.dao.StudentClassDao;
import com.swinburne.studentsurvey.domain.StudentClass;
import com.swinburne.studentsurvey.service.StudentClassService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentClassServiceImpl implements StudentClassService {

	@Resource
	private StudentClassDao studentClassDao;
	
	@Override
	public List<StudentClass> findAll(){
		return studentClassDao.findAll();
	}

	@Override
	public List<StudentClass> findStudentClassByStudentId(Long studentId){
		return studentClassDao.findStudentClassByStudentId(studentId);
	}

	@Override
	public Integer deleteStudentClassByStudentIdAndClassId(Integer studentId, Integer classId) {
		return studentClassDao.deleteStudentClassByStudentIdAndClassId(studentId, classId);
	};

	@Override
	public List<StudentClass> findStudentClassByStudentIdAndCourseId(Long studentId, Long classId) {
		return studentClassDao.findStudentClassByStudentIdAndCourseId(studentId, classId);
	};

	@Override
	public List<StudentClass> findStudentClassByStudentIdAndClassId(Long studentId, Long classId) {
		return studentClassDao.findStudentClassByStudentIdAndClassId(studentId, classId);
	};

	public List<StudentClass> findStudentClassByCourseId(Long courseId){
		return studentClassDao.findStudentClassByStudentId(courseId);
	}
	@Override
	public Integer save(StudentClass sc) {
		return studentClassDao.save(sc);
	}

}
