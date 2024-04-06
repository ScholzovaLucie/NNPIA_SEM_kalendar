package org.example.nnpia_sem_kalendar.Repository;

import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Entities.TypEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypEventsRepository extends JpaRepository<TypEvent, Long> {
}
