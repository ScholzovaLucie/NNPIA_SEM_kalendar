package org.example.nnpia_sem_kalendar.services;

import lombok.AllArgsConstructor;
import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Repository.ApplicationUserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService{

    ApplicationUserRepository repository;

    @Override
    public ApplicationUser verifyUser(String name, String password) {
        return repository.findByUsername(name, password);
    }
}
