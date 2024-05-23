package com.swinburne.studentsurvey.service;

import com.swinburne.studentsurvey.domain.AffiliationGroup;
import com.swinburne.studentsurvey.domain.NodeAnalytics;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NodeAnalyticsService {
    public List<NodeAnalytics> findNodeAnalyticsBySurveyDate(String surveyDate);
    public NodeAnalytics findNodeAnalyticsByParticipantId(Long id);
    public List<NodeAnalytics> findTopBySurveyDateAndType(String surveyDate, String type);

}
