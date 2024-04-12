package org.example.nnpia_sem_kalendar.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "PERSON")
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
    @ToString.Exclude
    private ApplicationUser user;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", holiday=" + holiday +
                '}';
    }
}
