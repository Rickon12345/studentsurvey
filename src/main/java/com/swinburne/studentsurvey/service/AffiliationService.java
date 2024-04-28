package com.swinburne.studentsurvey.service;

import com.swinburne.studentsurvey.domain.AffiliationGroup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AffiliationService {
    public List<AffiliationGroup> findAffiliationGroup(String surveyDate);

}
