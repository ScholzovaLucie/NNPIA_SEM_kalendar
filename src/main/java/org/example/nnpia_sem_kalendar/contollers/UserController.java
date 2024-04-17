package org.example.nnpia_sem_kalendar.contollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Entities.Person;
import org.example.nnpia_sem_kalendar.Repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@RestController
public class UserController {

    @Autowired
    public ApplicationUserRepository repository;

    @GetMapping(value = "/verifyUser")
    public ApplicationUser verifyUser(@RequestParam String username, @RequestParam String password) {
        ApplicationUser user = repository.findByUsernameAndPassword(username, password);
        return user;
    }

    @GetMapping(value = "/allUser")
    public List<ApplicationUser> allUser() {
        List<ApplicationUser> user = repository.getAllUsers();
        return user;
    }


    @PutMapping(value = "/createUser")
    public ApplicationUser createUser(@RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName) {
        ApplicationUser newUser = new ApplicationUser();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPassword(password);
        newUser.setUsername(username);

        repository.save(newUser);
        return repository.findByUsernameAndPassword(username, password);
    }

    @PutMapping(value = "/updateUser")
    public ApplicationUser updateUser(@RequestParam String username, @RequestParam String newusername, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName) throws UnsupportedEncodingException, ParseException, JsonProcessingException {
        ApplicationUser user = repository.findByUsername(username);
        if(user != null){
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);
            user.setUsername(newusername);

            repository.save(user);
            return repository.findByUsernameAndPassword(username, password);
        }
        return null;

    }
}
