package com.mateocuevas.challenge.repository;

import com.mateocuevas.challenge.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant,Long> {
    boolean existsByName(String name);
}
