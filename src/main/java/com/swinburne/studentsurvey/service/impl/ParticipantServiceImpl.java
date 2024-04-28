package com.swinburne.studentsurvey.service.impl;

import com.swinburne.studentsurvey.dao.ParticipantDao;
import com.swinburne.studentsurvey.dao.StudentDao;
import com.swinburne.studentsurvey.domain.Friends;
import com.swinburne.studentsurvey.domain.Participant;
import com.swinburne.studentsurvey.domain.Student;
import com.swinburne.studentsurvey.service.ParticipantService;
import com.swinburne.studentsurvey.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {
    @Resource
    private ParticipantDao participantDao;

    public ParticipantServiceImpl() {
    }

    public List<Participant> findByStudentId(Long id) {
        return this.participantDao.findByStudentId(id);
    }

    public Friends findFriendByParticipantId(Participant p){
        Friends f = new Friends();
        f.setParticipantId(p.getId());
        f.setParticipant(p);
        List<Participant> participants = this.participantDao.findFriendByParticipantId(p.getId());
        List<Friends> fl = new ArrayList<>();
        participants.forEach(i->{
            Friends friend = new Friends();
            friend.setParticipantId(i.getId());
            friend.setParticipant(i);
            List<Participant> fpl = this.participantDao.findFriendByParticipantId(i.getId());
            List<Friends> flChild = new ArrayList<>();
            fpl.forEach(j -> {Friends friendchild = new Friends(); friendchild.setParticipantId(j.getId()); friendchild.setParticipant(j);flChild.add(friendchild);});
            friend.setFl(flChild);
            fl.add(friend);
        });
        f.setFl(fl);
        return f;
    }
}
