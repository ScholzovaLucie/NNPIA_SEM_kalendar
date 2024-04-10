package org.example.nnpia_sem_kalendar.contollers;

import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Entities.Event;
import org.example.nnpia_sem_kalendar.Entities.TypEvent;
import org.example.nnpia_sem_kalendar.Repository.ApplicationUserRepository;
import org.example.nnpia_sem_kalendar.Repository.EventRepository;
import org.example.nnpia_sem_kalendar.Repository.TypEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
public class TypeTakController {

    @Autowired
    public ApplicationUserRepository userRepository;

    @Autowired
    public TypEventsRepository typEventsRepository;

    @GetMapping(value = "/allTypeEvents")
    public List<TypEvent> allTypes(@RequestParam String username) {
        ApplicationUser user = userRepository.findByUsername(username);

        if(user != null){
            List<TypEvent> events = typEventsRepository.getAll(user.getId());
            return events;
        }
        return null;
    }
}
