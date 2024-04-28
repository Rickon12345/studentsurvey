package com.swinburne.studentsurvey.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swinburne.studentsurvey.domain.User;

@Service
public interface UserService {
    List<User> users();

    Integer userSave(User var1);

    User userLogin(User var1);

    void userDelete(Long var1);

    User getUserById(Long var1);
}
