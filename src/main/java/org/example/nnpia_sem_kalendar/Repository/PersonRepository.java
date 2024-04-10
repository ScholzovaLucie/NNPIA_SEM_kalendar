package org.example.nnpia_sem_kalendar.Repository;

import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p where p.user.id = :id")
    List<Person> getAll(@Param("id") Long id);

    @Query("SELECT p FROM Person p where p.id = :id")
    Person getById(@Param("id") Long id);

}
