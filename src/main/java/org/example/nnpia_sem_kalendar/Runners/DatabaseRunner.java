package org.example.nnpia_sem_kalendar.Runners;

import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Entities.Person;
import org.example.nnpia_sem_kalendar.Repository.*;
import org.example.nnpia_sem_kalendar.service.NamedayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseRunner implements CommandLineRunner {

    private final EventRepository eventRepository;
    private final PersonRepository personRepository;
    private final ApplicationUserRepository userRepository;

    @Autowired
    public NamedayService holidayService;

    public DatabaseRunner(EventRepository eventRepository, PersonRepository personRepository, ApplicationUserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        Person person = new Person();
//        person.setLastName("Novák");
//        person.setFirstName("Jaroslav");
//        person.setBirthday(LocalDate.of(1998, 5, 16));
//
//        Set<Person> persons = new HashSet<>();
//        persons.add(person);
//
//        ApplicationUser user = new ApplicationUser();
//        user.setFirstName("Lucie");
//        user.setLastName("Scholzova");
//        user.setUsername("user");
//        user.setPassword("heslo");
//        user.setPersons(persons);
//        try{
//            LocalDate holiday = holidayService.getHolidayDateByName(person.getFirstName());
//            person.setHoliday(holiday);
//        }catch (Exception e){
//            System.out.println(e);
//        }
//
//        this.personRepository.save(person);
//        this.userRepository.save(user);

    }
}
