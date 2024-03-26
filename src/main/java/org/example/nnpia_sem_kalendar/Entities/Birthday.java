package org.example.nnpia_sem_kalendar.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BIRTHDAY")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Birthday {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDate birthDate;

    @OneToMany(mappedBy = "birthday")
    private Set<ApplicationUser> users = new HashSet<>();

}

