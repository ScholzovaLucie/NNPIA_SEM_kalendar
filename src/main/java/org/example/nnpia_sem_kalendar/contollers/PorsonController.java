package org.example.nnpia_sem_kalendar.contollers;

import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Entities.Person;
import org.example.nnpia_sem_kalendar.Repository.ApplicationUserRepository;
import org.example.nnpia_sem_kalendar.Repository.PersonRepository;
import org.example.nnpia_sem_kalendar.service.NamedayService;
import org.example.nnpia_sem_kalendar.service.NamedayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class PorsonController {

    @Autowired
    public PersonRepository repository;

    @Autowired
    public ApplicationUserRepository UserRepository;

    @Autowired
    public NamedayService holidayService;

    @GetMapping(value = "/getAllPersons")
    public List<Person> allUser(@RequestParam String username) {
        ApplicationUser user = UserRepository.findByUsername(username);
        if(user != null){
            List<Person> persons = repository.getAll(user.getId());
            return persons;
        }
        return null;
    }

    @PutMapping(value = "/updatePerson")
    public List<Person> updatePerson(@RequestParam String username, @RequestParam Long id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String birthday) {
        ApplicationUser user = UserRepository.findByUsername(username);
        if(user != null){
            Person currperson = repository.getById(id);

            String[] birthdayParts = birthday.split("-");

            currperson.setFirstName(firstName);
            currperson.setLastName(lastName);
            currperson.setBirthday(LocalDate.of(
                    Integer.valueOf(birthdayParts[0]),
                    Integer.valueOf(birthdayParts[1]),
                    Integer.valueOf(birthdayParts[2]))
            );

            repository.save(currperson);

            List<Person> persons = repository.getAll(user.getId());
            return persons;
        }
        return null;

    }

    @PutMapping(value = "/createPerson")
    public List<Person> createPerson(@RequestParam String username, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String birthday) {
        ApplicationUser user = UserRepository.findByUsername(username);
        if(user != null){
            Person newPerson = new Person();

            String[] birthdayParts = birthday.split("-");
            newPerson.setFirstName(firstName);
            newPerson.setLastName(lastName);
            newPerson.setBirthday(LocalDate.of(
                    Integer.valueOf(birthdayParts[0]),
                    Integer.valueOf(birthdayParts[1]),
                    Integer.valueOf(birthdayParts[2])));

            try{
                LocalDate holiday = holidayService.getHolidayDateByName(firstName);
                newPerson.setHoliday(holiday);
            }catch (Exception e){

            }
            newPerson.setUser(user);
            repository.save(newPerson);

            List<Person> persons = repository.getAll(user.getId());
            return persons;
        }
        return null;

    }


    @DeleteMapping(value = "/removePerson")
    public List<Person> removePerson(@RequestParam Long id, @RequestParam String username) {
        ApplicationUser user = UserRepository.findByUsername(username);
        if(user != null){
        Person person = repository.getById(id);
        if (person != null) {
            repository.delete(person);
        }
            List<Person> persons = repository.getAll(user.getId());
            return persons;
        }

        return null;
    }

    @GetMapping(value = "/getClosestBirthday")
    public Person getClosestBirthday(@RequestParam String username) {
        ApplicationUser user = UserRepository.findByUsername(username);
        if (user != null) {
            List<Person> persons = repository.getAll(user.getId());
            LocalDate today = LocalDate.now();
            Person closestPerson = null;
            long smallestDiff = Long.MAX_VALUE;

            for (Person person : persons) {
                LocalDate birthday = person.getBirthday();
                if (birthday == null) {
                    continue;
                }

                birthday = birthday.withYear(today.getYear());

                if (birthday.isBefore(today)) {
                    birthday = birthday.plusYears(1);
                }

                long diff = ChronoUnit.DAYS.between(today, birthday);

                if (diff < smallestDiff) {
                    smallestDiff = diff;
                    closestPerson = person;
                }
            }

            return closestPerson;
        }
        return null;
    }

    @GetMapping(value = "/getClosestNameDay")
    public Person getClosestNameDay(@RequestParam String username) {
        ApplicationUser user = UserRepository.findByUsername(username);
        if (user != null) {
            List<Person> persons = repository.getAll(user.getId());
            LocalDate today = LocalDate.now();
            Person closestPerson = null;
            long smallestDiff = Long.MAX_VALUE;

            for (Person person : persons) {
                LocalDate holiday = person.getHoliday();
                if (holiday == null) {
                    continue;
                }

                holiday = holiday.withYear(today.getYear());

                if (holiday.isBefore(today)) {
                    holiday = holiday.plusYears(1);
                }

                long diff = ChronoUnit.DAYS.between(today, holiday);

                if (diff < smallestDiff) {
                    smallestDiff = diff;
                    closestPerson = person;
                }
            }

            return closestPerson;
        }
        return null;
    }


}
