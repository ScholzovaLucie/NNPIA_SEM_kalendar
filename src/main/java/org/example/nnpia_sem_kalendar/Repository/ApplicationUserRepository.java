package org.example.nnpia_sem_kalendar.Repository;

import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    @Query("SELECT u FROM ApplicationUser u WHERE u.username = :username and u.password = :password")
    ApplicationUser findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("SELECT u FROM ApplicationUser u WHERE u.username = :username")
    ApplicationUser findByUsername(@Param("username") String username);

    @Query("SELECT COUNT(*) FROM ApplicationUser")
    int getCount();

    @Query("SELECT u FROM ApplicationUser u")
    List<ApplicationUser> getAllUsers();
}
