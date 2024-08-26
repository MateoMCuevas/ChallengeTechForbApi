package com.mateocuevas.challenge;

import com.mateocuevas.challenge.service.characteristic.CharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private CharacteristicService characteristicService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("EMPIEZA");
        characteristicService.saveCharacteristicsForInitialization();
    }
}
