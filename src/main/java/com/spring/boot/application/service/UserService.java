package com.spring.boot.application.service;

import com.spring.boot.application.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public Iterable<User>getAllUsers();
    public User createUser(User user) throws Exception;
}
