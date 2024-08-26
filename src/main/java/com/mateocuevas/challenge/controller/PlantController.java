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
@RequestMapping("/api/plants")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @GetMapping("/get-all")
    public List<PlantDto> getAllPlants() {
        System.out.println("Request to get plants received!");
        return plantService.getAllPlants();
    }

    @PostMapping("/create")
    public ResponseEntity<PlantDto> createPlant(@RequestBody PlantDto plantDto) {
        System.out.println("Request to create a plant received!");
         return ResponseEntity.ok(plantService.createPlant(plantDto));
    }
    @PutMapping("/update")
    public ResponseEntity<PlantDto> updatePlant(@RequestBody PlantDto plantDto) {
        System.out.println("Request to update a plant received!");
        return ResponseEntity.ok(plantService.updatePlant(plantDto));
    }
    @DeleteMapping("/delete/{plantId}")
    public ResponseEntity<Long> deletePlant(@PathVariable Long plantId) {
        System.out.println("Request to delete a plant received!");
        return ResponseEntity.ok(plantService.deletePlant(plantId));
    }
}
