package org.example.nnpia_sem_kalendar.Repository;

import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    @Query("SELECT u FROM ApplicationUser u WHERE u.username = :username and u.password = :password")
    ApplicationUser findByUsername(@Param("username") String username, @Param("password") String password);
}
