package com.swinburne.studentsurvey.service;

import com.swinburne.studentsurvey.domain.ClassAnalysis;
import com.swinburne.studentsurvey.domain.Classroom;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassroomService {

	public List<Classroom> findClassroomByClassId(Long classId);

	public Integer save(Classroom c);

	public List<ClassAnalysis> analysisClass();
}
