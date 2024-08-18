package com.mateocuevas.challenge.controller;

import com.mateocuevas.challenge.dto.PlantDto;
import com.mateocuevas.challenge.enums.Status;
import com.mateocuevas.challenge.entity.Plant;
import com.mateocuevas.challenge.entity.Reading;
import com.mateocuevas.challenge.service.plant.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plants")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @GetMapping("/get-all-with-alerts")
    public List<PlantDto> getPlantsWithAlerts() {
        return plantService.getPlantsWithAlerts();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createPlant(@RequestBody PlantDto plantDto) {
         plantService.createPlant(plantDto);
         return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
