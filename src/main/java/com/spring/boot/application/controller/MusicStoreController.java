package com.spring.boot.application.controller;


import com.spring.boot.application.entity.MusicStore;
import com.spring.boot.application.entity.Opinion;
import com.spring.boot.application.service.MusicStoreService;
import com.spring.boot.application.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MusicStoreController {
    @Autowired
    OpinionService opinionService;
    @Autowired
    MusicStoreService musicStoreService;

     @GetMapping("/musicStore")
    public String opinion(Model model) {
      model.addAttribute("opinion", new Opinion());
     model.addAttribute("opinions", opinionService.findAll());
     model.addAttribute("musicStore", new MusicStore());
     model.addAttribute("allStores", musicStoreService.findAll());
         model.addAttribute("listTab","active");

         return "musicStore-form/Music-view";
    }
    @PostMapping("/musicStore")
    public String createOpinion(Opinion opinion, Model model) {
        opinionService.save(opinion);
        model.addAttribute("opinion", new Opinion());
        model.addAttribute("opinions", opinionService.findAll());
        return "musicStore-form/Music-view";
    }
}
