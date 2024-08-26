package com.mateocuevas.challenge.service.plant;

import com.mateocuevas.challenge.dto.PlantDto;
import com.mateocuevas.challenge.entity.Plant;
import com.mateocuevas.challenge.entity.Reading;
import com.mateocuevas.challenge.enums.Status;

import java.util.List;
import java.util.Map;

public interface PlantService {
    List<PlantDto> getAllPlants();
    PlantDto createPlant(PlantDto plantDto);
    PlantDto updatePlant(PlantDto plantDto);
    Long deletePlant(Long plantId);

}
