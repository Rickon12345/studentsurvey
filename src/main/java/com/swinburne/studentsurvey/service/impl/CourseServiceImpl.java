package com.swinburne.studentsurvey.service.impl;

import java.util.List;

import com.swinburne.studentsurvey.dao.CourseDao;
import com.swinburne.studentsurvey.domain.Course;
import com.swinburne.studentsurvey.service.CourseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseDao courseDao;

	@Override
	public List<Course> courses() {
		return courseDao.findAll();
	}

	@Override
	public Integer courseSave(Course course) {
		return courseDao.save(course);
	}
	
	@Override
	public Course getCourseById(Long id) {
		return courseDao.findById(id);
	}
	
	@Override
	public void courseDelete(Long id) {
		courseDao.deleteById(id);
	}

}
