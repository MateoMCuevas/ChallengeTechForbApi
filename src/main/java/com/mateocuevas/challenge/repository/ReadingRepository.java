package com.mateocuevas.challenge.repository;

import com.mateocuevas.challenge.entity.Reading;
import com.mateocuevas.challenge.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingRepository extends JpaRepository<Reading,Long> {
    long countByStatus(Status status);
    long countByPlantIdAndStatus(Long plantId, Status status);
    void deleteByPlantId(Long plantId);
}
