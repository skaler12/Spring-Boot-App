package com.spring.boot.application.repository;

import com.spring.boot.application.entity.MusicStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface MusicStoreRepository extends CrudRepository<MusicStore,Integer>  {
    }

