package org.example.nnpia_sem_kalendar.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "ApplicationUser",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "password")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(unique=true, nullable = false)
    @JsonIgnore
    private String password;
    @Column(unique=true, nullable = false)
    private String username;

    @OneToMany(mappedBy = "user")
    private Set<Person> persons = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Event> events = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<TypEvent> typEvenets = new HashSet<>();
}
