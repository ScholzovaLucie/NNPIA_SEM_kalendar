package org.example.nnpia_sem_kalendar.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TYPEVENT")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
}
