package com.mateocuevas.challenge.service.plant;

import com.mateocuevas.challenge.dto.PlantDto;
import com.mateocuevas.challenge.entity.Plant;
import com.mateocuevas.challenge.entity.Reading;
import com.mateocuevas.challenge.enums.Status;
import com.mateocuevas.challenge.repository.PlantRepository;
import com.mateocuevas.challenge.repository.ReadingRepository;
import com.mateocuevas.challenge.service.characteristic.CharacteristicService;
import com.mateocuevas.challenge.service.reading.ReadingService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PlantServiceImpl implements PlantService{
    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private ReadingRepository readingRepository;
    @Autowired
    private ReadingService readingService;
    @Autowired
    private CharacteristicService characteristicService;

    public List<PlantDto> getAllPlants() {
        return plantRepository.findAll().stream()
                .map(plant -> PlantDto.builder()
                        .id(plant.getId())
                        .name(plant.getName())
                        .country(plant.getCountry())
                        .okCount(readingService.getReadingByPlantIdAndStatus(plant.getId(),Status.LECTURAS_OK))
                        .mediumAlertCount(readingService.getReadingByPlantIdAndStatus(plant.getId(),Status.ALERTAS_MEDIAS))
                        .redAlertCount(readingService.getReadingByPlantIdAndStatus(plant.getId(),Status.ALERTAS_ROJAS))
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PlantDto createPlant(PlantDto plantDto) {
        Plant plant=Plant.builder()
                .name(plantDto.getName())
                .country(plantDto.getCountry())
                .build();
        plant.setReadings(addReadings(plant,plantDto));
        if (plantRepository.existsByName(plant.getName())){System.out.println("plant creada");}
        plantRepository.save(plant);


        return plantDto;
    }

    private Reading createReading(Plant plant, Status status, PlantDto plantDto) {
        Reading reading = Reading.builder()
                .readingTime(LocalDateTime.now())
                .status(status)
                .plant(plant)
                .build();
        reading.setCharacteristic(characteristicService.addCharacteristicRandom(reading));
        return reading;
    }

    private List<Reading> addReadings(Plant plant,PlantDto plantDto)
    {
        List<Reading> readings=new ArrayList<>();
        readings.addAll(Stream.generate(() -> createReading(plant, Status.LECTURAS_OK, plantDto))
                .limit(plantDto.getOkCount())
                .toList());
        readings.addAll(Stream.generate(() -> createReading(plant, Status.ALERTAS_MEDIAS, plantDto))
                .limit(plantDto.getMediumAlertCount())
                .toList());
        readings.addAll(Stream.generate(() -> createReading(plant, Status.ALERTAS_ROJAS, plantDto))
                .limit(plantDto.getRedAlertCount())
                .toList());
        return readings;
    }

    @Transactional
    public PlantDto updatePlant(PlantDto plantDto){
        Optional<Plant> plantOptional = plantRepository.findById(plantDto.getId());
        if(plantOptional.isPresent()){
            Plant plant = plantOptional.get();
            plant.setCountry(plantDto.getCountry());
            plant.setName(plantDto.getName());
            readingRepository.deleteByPlantId(plant.getId());
            plant.setReadings(addReadings(plant,plantDto));
            plantRepository.save(plant);
        }else {throw new EntityNotFoundException(plantDto.getName()+"was not found");}
        return plantDto;
    }
    @Transactional
    public Long deletePlant(Long plantId){
        plantRepository.deleteById(plantId);
        return plantId;
    }
}
