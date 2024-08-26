package com.mateocuevas.challenge.entity;

import com.mateocuevas.challenge.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="plants",uniqueConstraints =@UniqueConstraint(columnNames = ("name")))
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String country;
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    private List<Reading> readings;


}
