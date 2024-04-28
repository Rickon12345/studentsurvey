package com.swinburne.studentsurvey.service.impl;

import com.swinburne.studentsurvey.dao.ClassroomDao;
import com.swinburne.studentsurvey.domain.ClassAnalysis;
import com.swinburne.studentsurvey.domain.Classroom;
import com.swinburne.studentsurvey.service.ClassroomService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService {

	@Resource
	private ClassroomDao classroomDao;

	@Override
	public List<Classroom> findClassroomByClassId(Long classId) {
		return classroomDao.findClassroomByClassId(classId);
	}

	@Override
	public Integer save(Classroom c) {
		return classroomDao.save(c);
	}

	public List<ClassAnalysis> analysisClass(){
		return classroomDao.analysisClass();
	}
}
