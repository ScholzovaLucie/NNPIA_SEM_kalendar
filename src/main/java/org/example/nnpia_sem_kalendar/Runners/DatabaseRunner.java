package org.example.nnpia_sem_kalendar.Runners;

import org.example.nnpia_sem_kalendar.Entities.Person;
import org.example.nnpia_sem_kalendar.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseRunner implements CommandLineRunner {

    private final BirthdayRepository birthdayRepository;
    private final EventRepository eventRepository;
    private final HolidayRepository holidayRepository;
    private final PersonRepository personRepository;
    private final ApplicationUserRepository userRepository;

    public DatabaseRunner(BirthdayRepository birthdayRepository, EventRepository eventRepository, HolidayRepository holidayRepository, PersonRepository personRepository, ApplicationUserRepository userRepository) {
        this.birthdayRepository = birthdayRepository;
        this.eventRepository = eventRepository;
        this.holidayRepository = holidayRepository;
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Person person = new Person();
        person.setFirstName("Lucie");
        person.setLastName("Scholzova");
        this.personRepository.save(person);
    }
}
