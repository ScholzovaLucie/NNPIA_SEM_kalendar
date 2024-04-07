package org.example.nnpia_sem_kalendar.Entities;

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
    @Column
    private String email;
    @Column(unique=true, nullable = false)
    private String password;
    @Column(unique=true, nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name="birthday_id")
    private Birthday birthday;

    @OneToMany(mappedBy = "user")
    private Set<Person> persons = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="holiday_id")
    private Holiday holiday;

    @OneToMany(mappedBy = "user")
    private Set<Event> events = new HashSet<>();
}
