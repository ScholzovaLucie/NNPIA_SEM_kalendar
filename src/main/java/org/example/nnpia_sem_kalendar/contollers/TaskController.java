package org.example.nnpia_sem_kalendar.contollers;

import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Entities.Task;
import org.example.nnpia_sem_kalendar.Entities.TypeTask;
import org.example.nnpia_sem_kalendar.Repository.ApplicationUserRepository;
import org.example.nnpia_sem_kalendar.Repository.TaskRepository;
import org.example.nnpia_sem_kalendar.Repository.TypeTasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
public class TaskController {
    @Autowired
    public ApplicationUserRepository userRepository;

    @Autowired
    public TaskRepository taskRepository;

    @Autowired
    public TypeTasksRepository typTasksRepository;

    @GetMapping(value = "/allTasks")
    public List<Task> allTasks(@RequestParam String username, @RequestParam String date, @RequestParam Long typeid) {
        ApplicationUser user = userRepository.findByUsername(username);
        LocalDate newdate;
        if(Objects.equals(date, "undefined")){
            newdate = LocalDate.now();
        }else{
            String[] dayParts = date.split("-");
            newdate = LocalDate.of(
                    Integer.valueOf(dayParts[0]),
                    Integer.valueOf(dayParts[1]),
                    Integer.valueOf(dayParts[2]));
        }
        if(user != null){
            TypeTask typ = typTasksRepository.getById(typeid);

            List<Task> Tasks;
            if(typ == null){
                Tasks = taskRepository.getAllByDate(user.getId(), newdate);
            }else{
                Tasks = taskRepository.getAllByDateAndType(user.getId(), newdate, typ);
            }
            return Tasks;


        }
        return null;
    }

    @PutMapping(value = "/createTask")
    public List<Task> createTask(
            @RequestParam String username,
            @RequestParam String date,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Long typeid,
            @RequestParam String time
    ) {
        LocalDate newdate;
        if(Objects.equals(date, "undefined")){
            newdate = LocalDate.now();
        }else{
            String[] dayParts = date.split("-");
            newdate = LocalDate.of(
                    Integer.valueOf(dayParts[0]),
                    Integer.valueOf(dayParts[1]),
                    Integer.valueOf(dayParts[2]));
        }
        ApplicationUser user = userRepository.findByUsername(username);
        TypeTask typ = typTasksRepository.getById(typeid);
        if(user != null){
            Task newTask = new Task();
            String[] timeParts = time.split(":");
            Time newTime = new Time(
                    Integer.valueOf(timeParts[0]),
                    Integer.valueOf(timeParts[1]),
                    0);


            newTask.setName(name);
            newTask.setDescription(description);
            newTask.setDate(newdate);
            newTask.setTime(newTime);
            newTask.setTyp(typ);

            newTask.setUser(user);
            taskRepository.save(newTask);

            List<Task> Tasks = taskRepository.getAllByDate(user.getId(), newdate);
            return Tasks;
        }
        return null;

    }

    @DeleteMapping(value = "/removeTask")
    public List<Task> removeTask(
            @RequestParam String username,
            @RequestParam Long id,
            @RequestParam String date
    ) {
        LocalDate newdate;
        if(Objects.equals(date, "undefined")){
            newdate = LocalDate.now();
        }else{
            String[] dayParts = date.split("-");
            newdate = LocalDate.of(
                    Integer.valueOf(dayParts[0]),
                    Integer.valueOf(dayParts[1]),
                    Integer.valueOf(dayParts[2]));
        }
        ApplicationUser user = userRepository.findByUsername(username);

        if(user != null){
            Task Task = taskRepository.getById(id);
            if (Task != null) {
                taskRepository.delete(Task);
            }
            List<Task> Tasks = taskRepository.getAllByDate(user.getId(), newdate);
            return Tasks;
        }

        return null;
    }

    @PutMapping(value = "/updateTask")
    public List<Task> updateTask(
            @RequestParam String username,
            @RequestParam Long id,
            @RequestParam String date,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Long typeid,
            @RequestParam String time
    ) {
        LocalDate newdate;
        if(Objects.equals(date, "undefined")){
            newdate = LocalDate.now();
        }else{
            String[] dayParts = date.split("-");
            newdate = LocalDate.of(
                    Integer.valueOf(dayParts[0]),
                    Integer.valueOf(dayParts[1]),
                    Integer.valueOf(dayParts[2]));
        }
        ApplicationUser user = userRepository.findByUsername(username);
        if(user != null){
            Task currTask = taskRepository.getById(id);
            TypeTask typ = typTasksRepository.getById(typeid);
            String[] timeParts = time.split(":");

            Time newTime = new Time(
                    Integer.valueOf(timeParts[0]),
                    Integer.valueOf(timeParts[1]),
                    0);


            currTask.setName(name);
            currTask.setDescription(description);
            currTask.setDate(newdate);
            currTask.setTime(newTime);
            currTask.setTyp(typ);

            taskRepository.save(currTask);

            List<Task> persons = taskRepository.getAllByDate(user.getId(), newdate);
            return persons;
        }
        return null;

    }



}
