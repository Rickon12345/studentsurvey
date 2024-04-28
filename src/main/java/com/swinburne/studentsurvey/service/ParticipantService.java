package com.swinburne.studentsurvey.service;

import com.swinburne.studentsurvey.domain.Friends;
import com.swinburne.studentsurvey.domain.Participant;
import com.swinburne.studentsurvey.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParticipantService {
    public List<Participant> findByStudentId(Long id);

    public Friends findFriendByParticipantId(Participant p);
}
