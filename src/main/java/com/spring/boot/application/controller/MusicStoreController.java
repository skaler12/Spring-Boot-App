package com.spring.boot.application.controller;


import com.spring.boot.application.entity.Opinion;
import com.spring.boot.application.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class MusicStoreController {
    @Autowired
    OpinionService opinionService;

     @GetMapping("/musicStore")
    public String opinion(Model model) {
      model.addAttribute("opinion", new Opinion());
     model.addAttribute("opinions", opinionService.findAll());
    return "MusicStore";
    }
    @PostMapping("/musicStore")
    public String createOpinion(Opinion opinion, Model model) {
        opinionService.save(opinion);
        model.addAttribute("opinion", new Opinion());
        model.addAttribute("opinions", opinionService.findAll());
        return "MusicStore";
    }
}
