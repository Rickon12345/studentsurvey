package com.swinburne.studentsurvey.service;

import com.swinburne.studentsurvey.domain.StudentClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentClassService {

	public List<StudentClass> findAll();

	public List<StudentClass> findStudentClassByStudentId(Long studentId);

	public Integer save(StudentClass sc);

	public Integer deleteStudentClassByStudentIdAndClassId(Integer studentId, Integer classId);

	public List<StudentClass> findStudentClassByStudentIdAndCourseId(Long studentId, Long classId);
	public List<StudentClass> findStudentClassByStudentIdAndClassId(Long studentId, Long classId);
	public List<StudentClass> findStudentClassByCourseId(Long courseId);
}
