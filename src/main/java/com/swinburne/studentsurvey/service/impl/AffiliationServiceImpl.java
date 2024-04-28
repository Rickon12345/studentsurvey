package com.swinburne.studentsurvey.service.impl;

import com.swinburne.studentsurvey.dao.AffiliationDao;
import com.swinburne.studentsurvey.domain.AffiliationGroup;
import com.swinburne.studentsurvey.service.AffiliationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AffiliationServiceImpl implements AffiliationService {
    @Resource
    private AffiliationDao affiliationDao;

    public AffiliationServiceImpl() {
    }

    public List<AffiliationGroup> findAffiliationGroup(String surveyDate) {
        return this.affiliationDao.groupAffiliation(surveyDate);
    }

}
