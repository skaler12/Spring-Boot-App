package com.spring.boot.application.service;

import com.spring.boot.application.entity.User;
import com.spring.boot.application.repository.RoleRepository;
import com.spring.boot.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public Iterable<User>getAllUsers(){
        return userRepository.findAll();
    }
    private boolean checkUsernameAvailable(User user) throws Exception {
        Optional<User> userFound = userRepository.findByUsername(user.getUsername());
        if (userFound.isPresent()) {
            throw new Exception("Username no avaible");
        }
        return true;
    }

    private boolean checkPasswordValid(User user) throws Exception {
        if ( !user.getPassword().equals(user.getConfirmPassword())) {
            throw new Exception("Password no confirm");
        }
        return true;
    }


    @Override
    public User createUser(User user) throws Exception {
        if (checkUsernameAvailable(user) && checkPasswordValid(user)) {
            user = userRepository.save(user);
        }
        return user;
    }
}
