package com.mateocuevas.challenge.controller;

import com.mateocuevas.challenge.dto.ReadingDto;
import com.mateocuevas.challenge.entity.Reading;
import com.mateocuevas.challenge.enums.Status;
import com.mateocuevas.challenge.service.reading.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/readings")
public class ReadingController {
    @Autowired
    private ReadingService readingService;

    @GetMapping("/get-all")
    public ReadingDto getAllReadings() {
        return readingService.getReadingsByStatus();
    }
}
