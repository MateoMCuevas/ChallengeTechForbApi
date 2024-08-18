package com.mateocuevas.challenge.service.plant;

import com.mateocuevas.challenge.dto.PlantDto;
import com.mateocuevas.challenge.entity.Plant;
import com.mateocuevas.challenge.entity.Reading;
import com.mateocuevas.challenge.enums.Status;
import com.mateocuevas.challenge.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PlantServiceImpl implements PlantService{
    @Autowired
    private PlantRepository plantRepository;

    @Override
    public void createPlant(PlantDto plantDto) {
        plantRepository.save(Plant.builder()
                .name(plantDto.getName())
                .country(plantDto.getCountry())
                .readings(addReadings(plantDto))
                .build());
    }

    private List<Reading> addReadings(PlantDto plantDto)
    {
        List<Reading> readings=new ArrayList<>();
        readings.addAll(Stream.generate(() -> Reading.builder()
                        .readingTime(LocalDateTime.now())
                        .status(Status.OK)
                        .build())
                .limit(plantDto.getOkCount())
                .toList());
        readings.addAll(Stream.generate(() -> Reading.builder()
                        .readingTime(LocalDateTime.now())
                        .status(Status.ALERTA_MEDIANA)
                        .build())
                .limit(plantDto.getMediumAlertCount())
                .toList());
        readings.addAll(Stream.generate(() -> Reading.builder()
                        .readingTime(LocalDateTime.now())
                        .status(Status.ALERTA_ROJA)
                        .build())
                .limit(plantDto.getRedAlertCount())
                .toList());
        return readings;
    }

    public List<PlantDto> getPlantsWithAlerts() {
        return plantRepository.findAll().stream()
                .map(plant -> PlantDto.builder()
                        .name(plant.getName())
                        .country(plant.getCountry())
                        .okCount(getCountByStatus(plant,Status.OK))
                        .mediumAlertCount(getCountByStatus(plant,Status.ALERTA_MEDIANA))
                        .redAlertCount(getCountByStatus(plant,Status.ALERTA_ROJA))
                        .build())
                .collect(Collectors.toList());
    }

    private long getCountByStatus(Plant plant,Status status) {
        return plant.getReadings().stream()
                .filter(reading -> reading.getStatus() == status)
                .count();
    }
}
