package org.example.nnpia_sem_kalendar.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "Task")
@NoArgsConstructor
@Data
public class Task {
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
    @ToString.Exclude
    private TypeTask typ;

    @ManyToOne
    @JoinColumn(name="user_id")
    @ToString.Exclude
    private ApplicationUser user;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
