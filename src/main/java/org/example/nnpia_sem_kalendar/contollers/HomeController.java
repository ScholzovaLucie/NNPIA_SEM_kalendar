package org.example.nnpia_sem_kalendar.contollers;

import org.example.nnpia_sem_kalendar.services.KalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    @Autowired
    public KalendarService kalendarService;

    @GetMapping("/home")
    public String greeting(Model model) {
        model.addAttribute("day", kalendarService.getDay());
        return "home";
    }

    @GetMapping("/getPersons")
    public String getPersons(Model model) {
        model.addAttribute("day", kalendarService.getDay());
        return "home";
    }

}