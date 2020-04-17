package com.spring.boot.application.service;

import com.spring.boot.application.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    //wszyscy urzytkownicy
    public Iterable<User>getAllUsers();
    //utworz urzytkownika
    public User createUser(User user) throws Exception;
    //daj urzytkownika po id
    public User getUserById(Long id)throws Exception;
    //uaktualnij dane urzytkwnia
    public User updateUser(User user)throws Exception;
    //usun urzytkownika
    public void deleteUser(Long id) throws Exception;
}
