package org.example.nnpia_sem_kalendar.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "HOLIDAY")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private LocalDate date;

    @OneToMany(mappedBy = "holiday")
    private Set<ApplicationUser> users = new HashSet<>();

}
