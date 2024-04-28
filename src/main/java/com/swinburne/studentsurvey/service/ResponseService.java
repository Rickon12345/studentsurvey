package com.swinburne.studentsurvey.service;

import com.swinburne.studentsurvey.domain.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResponseService {
    public Response findByStudentIdAndSemester(String surveyDate, Long id);
    public Response findClassAvg(String surveyDate, String house);
    public Response findSchoolAvg(String surveyDate);

}
