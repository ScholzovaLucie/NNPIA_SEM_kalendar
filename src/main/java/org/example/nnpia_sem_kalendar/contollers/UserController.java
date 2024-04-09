package org.example.nnpia_sem_kalendar.contollers;

import com.github.lambdaexpression.annotation.RequestBodyParam;
import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

}
