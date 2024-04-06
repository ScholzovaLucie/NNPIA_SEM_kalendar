package org.example.nnpia_sem_kalendar.services;

import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;

public interface IUserService {

    ApplicationUser verifyUser(String name, String password);
}
