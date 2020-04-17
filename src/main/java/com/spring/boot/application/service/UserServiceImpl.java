package com.spring.boot.application.service;

import com.spring.boot.application.entity.User;
import com.spring.boot.application.repository.RoleRepository;
import com.spring.boot.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public Iterable<User>getAllUsers(){
        return userRepository.findAll();
    }
}
