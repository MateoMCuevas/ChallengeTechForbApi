package com.mateocuevas.challenge.controller;

import com.mateocuevas.challenge.dto.CharacteristicDto;
import com.mateocuevas.challenge.entity.Characteristic;
import com.mateocuevas.challenge.entity.Reading;
import com.mateocuevas.challenge.enums.Status;
import com.mateocuevas.challenge.service.characteristic.CharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/characteristics")
public class CharacteristicController {
    @Autowired
    private CharacteristicService characteristicService;

    @GetMapping("/get-all-with-alerts")
    public List<CharacteristicDto> getCharacteristicsWithAlerts() {
        return characteristicService.getCharacteristicsWithAlerts();
    }
}
