package com.mateocuevas.challenge.service.reading;

import com.mateocuevas.challenge.dto.PlantDto;
import com.mateocuevas.challenge.dto.ReadingDto;
import com.mateocuevas.challenge.entity.Reading;
import com.mateocuevas.challenge.enums.Status;
import com.mateocuevas.challenge.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadingServiceImpl implements ReadingService{
    @Autowired
    private ReadingRepository readingRepository;

    public List<ReadingDto> getReadingsByStatus() {
        return Arrays.stream(Status.values())
                .map(status -> ReadingDto.builder()
                        .name(status.name())
                        .count(readingRepository.countByStatus(status))
                        .build())
                .collect(Collectors.toList());
    }

    public Long getReadingByPlantIdAndStatus(Long plantId, Status status){
        return readingRepository.countByPlantIdAndStatus(plantId, status);
    }


}
