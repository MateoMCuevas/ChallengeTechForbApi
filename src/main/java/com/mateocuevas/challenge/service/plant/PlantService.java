package com.mateocuevas.challenge.service.plant;

import com.mateocuevas.challenge.dto.PlantDto;
import com.mateocuevas.challenge.entity.Plant;
import com.mateocuevas.challenge.entity.Reading;
import com.mateocuevas.challenge.enums.Status;

import java.util.List;
import java.util.Map;

public interface PlantService {
    List<PlantDto> getPlantsWithAlerts();
    void createPlant(PlantDto plantDto);

}
