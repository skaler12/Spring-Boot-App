package com.spring.boot.application.controller;


import com.spring.boot.application.entity.MusicStore;
import com.spring.boot.application.entity.Opinion;
import com.spring.boot.application.service.MusicStoreService;
import com.spring.boot.application.service.OpinionService;
import com.spring.boot.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MusicStoreController {
    @Autowired
    OpinionService opinionService;
    @Autowired
    MusicStoreService musicStoreService;
    @Autowired
    UserService userService;

   // private Covid19Parser covid19Parser;

//    public MusicStoreController(Covid19Parser covid19Parser) {
  //      this.covid19Parser = covid19Parser;
   // }


    @GetMapping("/musicStore")
    public String opinion(Model model) {
        model.addAttribute("opinion", new Opinion());
        model.addAttribute("opinions", opinionService.findAll());
        model.addAttribute("musicStore", new MusicStore());
        model.addAttribute("allStores", musicStoreService.findAll());
        model.addAttribute("listTab", "active");

       // model.addAttribute("points", covid19Parser.getCovidData());


        return "musicStore-form/Music-view";
    }

    @PostMapping("/musicStore")
    public String createOpinion(Opinion opinion, Model model, MusicStore musicStore) {
        opinionService.save(opinion);
        model.addAttribute("opinion", new Opinion());
        model.addAttribute("opinions", opinionService.findAll());
        model.addAttribute("stores", musicStoreService.findAll());
        return "musicStore-form/Music-view";
    }
    /*@GetMapping("/musicStore/{storeId}")
    public String getDetails(Model model, @PathVariable(name = "storeId") Integer storeId) {
        Optional<MusicStore> musicStoreById = Optional.of(musicStoreService.findById(storeId).get());
        model.addAttribute("musicStoreById", musicStoreById);
        model.addAttribute("allStores", musicStoreService.findAll());
        return "musicStore-form/Music-view";
    }*/

    @RequestMapping("/music")
    public String getStudent(@RequestParam("id") Integer id, Model model) {
       MusicStore musicStore = musicStoreService.findById(id).get();
        model.addAttribute("MusicStore", musicStore);
        return "music";
    }
}
