package org.example.nnpia_sem_kalendar.contollers;

import com.github.lambdaexpression.annotation.RequestBodyParam;
import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    public ApplicationUserRepository repository;

    @PostMapping(value = "/verifyUser")
    @ResponseBody
    public ApplicationUser verifyUser(@RequestBodyParam String username, @RequestBodyParam String password) {
        ApplicationUser user = repository.findByUsernameAndPassword(username, password);
        return user;
    }

}
