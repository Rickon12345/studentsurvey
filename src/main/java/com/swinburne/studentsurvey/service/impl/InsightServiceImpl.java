package com.swinburne.studentsurvey.service.impl;

import com.swinburne.studentsurvey.dao.InsightDao;
import com.swinburne.studentsurvey.domain.Insight;
import com.swinburne.studentsurvey.service.InsightService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsightServiceImpl implements InsightService {
    @Resource
    private InsightDao insightDao;

    public InsightServiceImpl() {
    }

    public List<Insight> findInsightBySemester(String surveyDate) {
        return insightDao.findInsightBySemester(surveyDate);
    }
}
