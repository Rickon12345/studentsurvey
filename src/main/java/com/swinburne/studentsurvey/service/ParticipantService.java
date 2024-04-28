package com.swinburne.studentsurvey.service;

import com.swinburne.studentsurvey.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParticipantService {
    public List<Participant> findByStudentId(Long id);
    public List<Participant> findAllBySemester(String surveyDate);
    public Participant findByStudentIdAndSemester(String surveyDate,Long id);

    public Friends findFriendByParticipantId(Participant p);
    public Influential findInfluentialByParticipantId(Participant p);
    public Disrespect findDisrespectByParticipantId(Participant p);
}
