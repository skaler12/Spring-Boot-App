package com.spring.boot.application.service;

import com.spring.boot.application.entity.Idea;
import com.spring.boot.application.entity.Opinion;
import com.spring.boot.application.repository.NewIdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdeaService {
    @Autowired
    NewIdeaRepository newIdeaRepository;

    public Iterable<Idea> findAll(){return newIdeaRepository.findAll();}
    public Idea save(Idea idea){return  newIdeaRepository.save(idea);}
    public Optional<Idea> findById(Integer id){return newIdeaRepository.findById(id);}
    public void delete(Idea idea){newIdeaRepository.delete(idea);}
}
