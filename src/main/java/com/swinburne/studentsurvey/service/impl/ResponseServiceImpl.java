package com.swinburne.studentsurvey.service.impl;

import com.swinburne.studentsurvey.dao.AffiliationDao;
import com.swinburne.studentsurvey.dao.ResponseDao;
import com.swinburne.studentsurvey.domain.AffiliationGroup;
import com.swinburne.studentsurvey.domain.Response;
import com.swinburne.studentsurvey.service.AffiliationService;
import com.swinburne.studentsurvey.service.ResponseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseServiceImpl implements ResponseService {
    @Resource
    private ResponseDao responseDao;

    public ResponseServiceImpl() {
    }

    public Response findByStudentIdAndSemester(String surveyDate, Long id) {
        return responseDao.findByStudentIdAndSemester(surveyDate, id);
    }
    public Response findByParticipantId(Long id){
        return responseDao.findByParticipantId(id);
    }
    public Response findClassAvg(String surveyDate, String house) {
        return responseDao.findClassAvg(surveyDate, house);
    }
    public Response findSchoolAvg(String surveyDate) {
        return responseDao.findSchoolAvg(surveyDate);
    }
}
