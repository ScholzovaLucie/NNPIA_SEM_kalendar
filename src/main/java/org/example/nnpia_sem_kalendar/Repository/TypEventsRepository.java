package org.example.nnpia_sem_kalendar.Repository;

import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Entities.Person;
import org.example.nnpia_sem_kalendar.Entities.TypEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypEventsRepository extends JpaRepository<TypEvent, Long> {
    @Query("SELECT t FROM TypEvent t where t.user.id = :id")
    List<TypEvent> getAll(@Param("id") Long id);
}
