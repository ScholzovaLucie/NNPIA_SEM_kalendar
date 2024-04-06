package org.example.nnpia_sem_kalendar.contollers;

import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.services.KalendarService;
import org.example.nnpia_sem_kalendar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/verifyUser")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApplicationUser verifyUser(@RequestBody String username, @RequestBody String password) {
        return userService.verifyUser(username, password);
    }

}
