package org.example.nnpia_sem_kalendar.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "PERSON")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate holiday;

    @ManyToOne
    @JoinColumn(name="user_id")
    private ApplicationUser user;

}

