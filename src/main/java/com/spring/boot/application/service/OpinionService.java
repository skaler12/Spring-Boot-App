package com.spring.boot.application.service;

import com.spring.boot.application.UsernameOrIdNotFound;
import com.spring.boot.application.dto.ChangePasswordForm;
import com.spring.boot.application.entity.Opinion;
import com.spring.boot.application.entity.User;
import com.spring.boot.application.repository.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OpinionService {
    @Autowired
    OpinionRepository opinionRepository;

    public Iterable<Opinion> findAll(){return opinionRepository.findAll();}
    public Opinion save(Opinion opinion){return  opinionRepository.save(opinion);}
    public Optional<Opinion> findById(Long id){return opinionRepository.findById(id);}
    public void delete(Opinion opinion){opinionRepository.delete(opinion);}


}
