package org.example.nnpia_sem_kalendar.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "EVENT")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    @Column
    @JsonFormat(pattern="HH:mm")
    private Time time;

    @ManyToOne
    @JoinColumn(name="typ_id")
    private TypEvent typ;

    @ManyToOne
    @JoinColumn(name="user_id")
    private ApplicationUser user;

}

