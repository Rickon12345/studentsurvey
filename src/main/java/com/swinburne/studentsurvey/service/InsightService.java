package com.swinburne.studentsurvey.service;

import com.swinburne.studentsurvey.domain.Insight;
import com.swinburne.studentsurvey.domain.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InsightService {
    public List<Insight> findInsightBySemester(String surveyDate);
}
