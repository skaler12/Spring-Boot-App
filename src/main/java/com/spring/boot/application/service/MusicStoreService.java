package com.spring.boot.application.service;

import com.spring.boot.application.entity.MusicStore;
import com.spring.boot.application.repository.MusicStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MusicStoreService {
    @Autowired
    MusicStoreRepository musicStoreRepository;

    public Iterable<MusicStore> findAll(){return musicStoreRepository.findAll();}
    public MusicStore save(MusicStore musicStore){return  musicStoreRepository.save(musicStore);}
    public Optional<MusicStore> findById(Long id){return musicStoreRepository.findById(id);}
    public void delete(MusicStore musicStore){musicStoreRepository.delete(musicStore);}

    //public MusicStore getMusicStore(Long storeId) {
      //  return musicStoreRepository.getMusicStoreBy(storeId);
    //}


}
