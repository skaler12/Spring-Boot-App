package com.spring.boot.application.repository;

import com.spring.boot.application.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    //metoda szukajaca usera po username
    public Optional<User>findByUsername(String username);
}
