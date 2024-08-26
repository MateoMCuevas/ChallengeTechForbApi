package com.mateocuevas.challenge.controller;

import com.mateocuevas.challenge.dto.CharacteristicDto;
import com.mateocuevas.challenge.entity.Characteristic;
import com.mateocuevas.challenge.entity.Reading;
import com.mateocuevas.challenge.enums.Status;
import com.mateocuevas.challenge.service.characteristic.CharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/characteristics")
public class CharacteristicController {
    @Autowired
    private CharacteristicService characteristicService;

    @GetMapping("/get-all")
    public List<CharacteristicDto> getCharacteristicsWithAlerts() {
        return characteristicService.getAllCharacteristics();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody String name) {
         characteristicService.create(name);
         return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
