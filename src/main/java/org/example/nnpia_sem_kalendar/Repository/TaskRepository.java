package org.example.nnpia_sem_kalendar.Repository;

import org.example.nnpia_sem_kalendar.Entities.Task;
import org.example.nnpia_sem_kalendar.Entities.TypeTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT e FROM Task e where e.user.id = :id and e.date = :date")
    List<Task> getAllByDate(@Param("id") Long id, @Param("date") LocalDate date);

    @Query("SELECT e FROM Task e where e.user.id = :id and e.date = :date and e.typ = :typ")
    List<Task> getAllByDateAndType(@Param("id") Long id, @Param("date") LocalDate date, @Param("date") TypeTask typ);

    @Query("SELECT e FROM Task e where e.id = :id")
    Task getById(@Param("id") Long id);
}
