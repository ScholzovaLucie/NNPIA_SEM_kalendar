package org.example.nnpia_sem_kalendar.contollers;

import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Entities.TypeTask;
import org.example.nnpia_sem_kalendar.Repository.ApplicationUserRepository;
import org.example.nnpia_sem_kalendar.Repository.TypeTasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TypeTasksController {

    @Autowired
    public ApplicationUserRepository userRepository;

    @Autowired
    public TypeTasksRepository typeTasksRepository;

    @GetMapping(value = "/allTypeEvents")
    public List<TypeTask> allTypes(@RequestParam String username) {
        ApplicationUser user = userRepository.findByUsername(username);

        if(user != null){
            List<TypeTask> events = typeTasksRepository.getAll(user.getId());
            return events;
        }
        return null;
    }

    @PutMapping(value = "/createTypeTask")
    public List<TypeTask> createTypeTask(@RequestParam String username, @RequestParam String name) {
        ApplicationUser user = userRepository.findByUsername(username);

        if(user != null){
            TypeTask newType = new TypeTask();
            newType.setName(name);
            newType.setUser(user);
            typeTasksRepository.save(newType);
            List<TypeTask> events = typeTasksRepository.getAll(user.getId());
            return events;
        }
        return null;
    }

    @DeleteMapping(value = "/remove")
    public List<TypeTask> remove(@RequestParam String username, @RequestParam Long id) {
        ApplicationUser user = userRepository.findByUsername(username);

        if(user != null){
            TypeTask type = typeTasksRepository.getById(id);
            typeTasksRepository.delete(type);
            List<TypeTask> events = typeTasksRepository.getAll(user.getId());
            return events;
        }
        return null;
    }
    @PostMapping(value = "/update")
    public List<TypeTask> update(@RequestParam String username, @RequestParam Long id, @RequestParam String name) {
        ApplicationUser user = userRepository.findByUsername(username);

        if(user != null){
            TypeTask type = typeTasksRepository.getById(id);
            type.setName(name);
            typeTasksRepository.save(type);
            List<TypeTask> events = typeTasksRepository.getAll(user.getId());
            return events;
        }
        return null;
    }

}
