package com.swinburne.studentsurvey.service.impl;

import com.swinburne.studentsurvey.dao.AffiliationDao;
import com.swinburne.studentsurvey.dao.NodeAnalyticsDao;
import com.swinburne.studentsurvey.domain.AffiliationGroup;
import com.swinburne.studentsurvey.domain.NodeAnalytics;
import com.swinburne.studentsurvey.service.AffiliationService;
import com.swinburne.studentsurvey.service.NodeAnalyticsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeAnalyticsServiceImpl implements NodeAnalyticsService {
    @Resource
    private NodeAnalyticsDao nodeAnalyticsDao;

    public NodeAnalyticsServiceImpl() {
    }

    public List<NodeAnalytics> findNodeAnalyticsBySurveyDate(String surveyDate) {
        return this.nodeAnalyticsDao.findNodeAnalyticsBySurveyDate(surveyDate);
    }
    public NodeAnalytics findNodeAnalyticsByParticipantId(Long id){
        return this.nodeAnalyticsDao.findNodeAnalyticsByParticipantId(id);
    }
    public List<NodeAnalytics> findTopBySurveyDateAndType(String surveyDate, String type) {
        return this.nodeAnalyticsDao.findTopBySurveyDateAndType(surveyDate, type);
    }


}
