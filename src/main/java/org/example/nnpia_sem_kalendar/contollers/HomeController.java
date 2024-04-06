package org.example.nnpia_sem_kalendar.contollers;

import org.example.nnpia_sem_kalendar.services.KalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@RestController
public class HomeController {

    @Autowired
    public KalendarService kalendarService;

    @GetMapping("/home")
    public String greeting() {
        return kalendarService.getDay();
    }

    @GetMapping("/getPersons")
    public String getPersons() {
        return "home";
    }

}