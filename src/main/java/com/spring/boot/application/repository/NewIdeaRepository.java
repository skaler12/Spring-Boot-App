package com.spring.boot.application.repository;

import com.spring.boot.application.entity.Idea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewIdeaRepository extends CrudRepository<Idea,Integer> {
}
