package com.spring.boot.application.repository;

import com.spring.boot.application.entity.Opinion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionRepository extends CrudRepository<Opinion,Integer> {
}
