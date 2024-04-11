package org.example.nnpia_sem_kalendar.contollers;

import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Entities.Event;
import org.example.nnpia_sem_kalendar.Entities.Person;
import org.example.nnpia_sem_kalendar.Entities.TypEvent;
import org.example.nnpia_sem_kalendar.Repository.ApplicationUserRepository;
import org.example.nnpia_sem_kalendar.Repository.EventRepository;
import org.example.nnpia_sem_kalendar.Repository.PersonRepository;
import org.example.nnpia_sem_kalendar.Repository.TypEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
public class EventController {
    @Autowired
    public ApplicationUserRepository userRepository;

    @Autowired
    public EventRepository eventRepository;

    @Autowired
    public TypEventsRepository typEventsRepository;

    @GetMapping(value = "/allEvents")
    public List<Event> allEvents(@RequestParam String username, @RequestParam String date, @RequestParam Long typeid) {
        ApplicationUser user = userRepository.findByUsername(username);
        LocalDate newdate;
        if(Objects.equals(date, "undefined")){
            newdate = LocalDate.now();
        }else{
            String[] dayParts = date.split("-");
            newdate = LocalDate.of(
                    Integer.valueOf(dayParts[0]),
                    Integer.valueOf(dayParts[1]),
                    Integer.valueOf(dayParts[2]));
        }
        if(user != null){
            TypEvent typ = typEventsRepository.getById(typeid);

            List<Event> events;
            if(typ == null){
                events = eventRepository.getAllByDate(user.getId(), newdate);
            }else{
                events = eventRepository.getAllByDateAndType(user.getId(), newdate, typ);
            }
            return events;


        }
        return null;
    }

    @PutMapping(value = "/createTask")
    public List<Event> createTask(
            @RequestParam String username,
            @RequestParam String date,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Long typeid,
            @RequestParam String time
    ) {
        LocalDate newdate;
        if(Objects.equals(date, "undefined")){
            newdate = LocalDate.now();
        }else{
            String[] dayParts = date.split("-");
            newdate = LocalDate.of(
                    Integer.valueOf(dayParts[0]),
                    Integer.valueOf(dayParts[1]),
                    Integer.valueOf(dayParts[2]));
        }
        ApplicationUser user = userRepository.findByUsername(username);
        TypEvent typ = typEventsRepository.getById(typeid);
        if(user != null){
            Event newTask = new Event();
            String[] timeParts = time.split(":");
            Time newTime = new Time(
                    Integer.valueOf(timeParts[0]),
                    Integer.valueOf(timeParts[1]),
                    0);


            newTask.setName(name);
            newTask.setDescription(description);
            newTask.setDate(newdate);
            newTask.setTime(newTime);
            newTask.setTyp(typ);

            newTask.setUser(user);
            eventRepository.save(newTask);

            List<Event> events = eventRepository.getAllByDate(user.getId(), newdate);
            return events;
        }
        return null;

    }

    @DeleteMapping(value = "/removeTask")
    public List<Event> removeTask(
            @RequestParam String username,
            @RequestParam Long id,
            @RequestParam String date
    ) {
        LocalDate newdate;
        if(Objects.equals(date, "undefined")){
            newdate = LocalDate.now();
        }else{
            String[] dayParts = date.split("-");
            newdate = LocalDate.of(
                    Integer.valueOf(dayParts[0]),
                    Integer.valueOf(dayParts[1]),
                    Integer.valueOf(dayParts[2]));
        }
        ApplicationUser user = userRepository.findByUsername(username);

        if(user != null){
            Event event = eventRepository.getById(id);
            if (event != null) {
                eventRepository.delete(event);
            }
            List<Event> events = eventRepository.getAllByDate(user.getId(), newdate);
            return events;
        }

        return null;
    }

    @PutMapping(value = "/updateTask")
    public List<Event> updateTask(
            @RequestParam String username,
            @RequestParam Long id,
            @RequestParam String date,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Long typeid,
            @RequestParam String time
    ) {
        LocalDate newdate;
        if(Objects.equals(date, "undefined")){
            newdate = LocalDate.now();
        }else{
            String[] dayParts = date.split("-");
            newdate = LocalDate.of(
                    Integer.valueOf(dayParts[0]),
                    Integer.valueOf(dayParts[1]),
                    Integer.valueOf(dayParts[2]));
        }
        ApplicationUser user = userRepository.findByUsername(username);
        if(user != null){
            Event currEvent = eventRepository.getById(id);
            TypEvent typ = typEventsRepository.getById(typeid);
            String[] timeParts = time.split(":");

            Time newTime = new Time(
                    Integer.valueOf(timeParts[0]),
                    Integer.valueOf(timeParts[1]),
                    0);


            currEvent.setName(name);
            currEvent.setDescription(description);
            currEvent.setDate(newdate);
            currEvent.setTime(newTime);
            currEvent.setTyp(typ);

            eventRepository.save(currEvent);

            List<Event> persons = eventRepository.getAllByDate(user.getId(), newdate);
            return persons;
        }
        return null;

    }



}
