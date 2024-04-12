package org.example.nnpia_sem_kalendar.Repository;

import org.example.nnpia_sem_kalendar.Entities.TypeTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeTasksRepository extends JpaRepository<TypeTask, Long> {
    @Query("SELECT t FROM TypeTask t where t.user.id = :id")
    List<TypeTask> getAll(@Param("id") Long id);

    @Query("SELECT t FROM TypeTask t where t.id = :id")
    TypeTask getById(@Param("id") Long id);
}
