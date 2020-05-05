package com.spring.boot.application.repository;

import com.spring.boot.application.entity.Opinion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface OpinionRepository extends CrudRepository<Opinion,Integer> {
}