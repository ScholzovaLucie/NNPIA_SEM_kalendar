package org.example.nnpia_sem_kalendar.contollers;

import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Entities.Event;
import org.example.nnpia_sem_kalendar.Entities.TypEvent;
import org.example.nnpia_sem_kalendar.Repository.ApplicationUserRepository;
import org.example.nnpia_sem_kalendar.Repository.EventRepository;
import org.example.nnpia_sem_kalendar.Repository.TypEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
public class TypeTaskController {

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

    @PutMapping(value = "/createTypeTask")
    public List<TypEvent> createTypeTask(@RequestParam String username, @RequestParam String name) {
        ApplicationUser user = userRepository.findByUsername(username);

        if(user != null){
            TypEvent newType = new TypEvent();
            newType.setName(name);
            newType.setUser(user);
            typEventsRepository.save(newType);
            List<TypEvent> events = typEventsRepository.getAll(user.getId());
            return events;
        }
        return null;
    }

    @DeleteMapping(value = "/removeTypeTask")
    public List<TypEvent> removeTypeTask(@RequestParam String username, @RequestParam Long id) {
        ApplicationUser user = userRepository.findByUsername(username);

        if(user != null){
            TypEvent type = typEventsRepository.getById(id);
            typEventsRepository.delete(type);
            List<TypEvent> events = typEventsRepository.getAll(user.getId());
            return events;
        }
        return null;
    }
    @PostMapping(value = "/updateTypeTask")
    public List<TypEvent> updateTypeTask(@RequestParam String username, @RequestParam Long id, @RequestParam String name) {
        ApplicationUser user = userRepository.findByUsername(username);

        if(user != null){
            TypEvent type = typEventsRepository.getById(id);
            type.setName(name);
            typEventsRepository.save(type);
            List<TypEvent> events = typEventsRepository.getAll(user.getId());
            return events;
        }
        return null;
    }

}
