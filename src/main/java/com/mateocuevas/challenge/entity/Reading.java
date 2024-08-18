package com.mateocuevas.challenge.entity;

import com.mateocuevas.challenge.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "readings")
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime readingTime;
    @ManyToOne
    @JoinColumn(name = "installation_id")
    private Plant plant;
    @ManyToOne
    @JoinColumn(name = "characteristic_id")
    private Characteristic characteristic;
    @Enumerated(EnumType.STRING)
    private Status status;
}
