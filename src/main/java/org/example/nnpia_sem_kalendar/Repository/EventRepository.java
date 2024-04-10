package org.example.nnpia_sem_kalendar.Repository;

import org.example.nnpia_sem_kalendar.Entities.Event;
import org.example.nnpia_sem_kalendar.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e where e.user.id = :id and e.date = :date")
    List<Event> getAllByDate(@Param("id") Long id, @Param("date") LocalDate date);

    @Query("SELECT e FROM Event e where e.id = :id")
    Event getById(@Param("id") Long id);
}
