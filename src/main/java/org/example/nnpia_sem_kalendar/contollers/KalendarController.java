package org.example.nnpia_sem_kalendar.contollers;

import org.example.nnpia_sem_kalendar.services.KalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KalendarController {

    public static final String APP_USER_PATH = "/v1";

    @Autowired
    public KalendarService kalendarService;

    @RequestMapping(value= APP_USER_PATH, method= RequestMethod.GET)
    public String hallo(){
        return  "hellow";
    }

    @RequestMapping(value=APP_USER_PATH + "/day", method= RequestMethod.GET)
    public String getDay(){
        return  "Day "+ kalendarService.getDay();
    }

}