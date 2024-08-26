package com.mateocuevas.challenge.service.characteristic;

import com.mateocuevas.challenge.dto.CharacteristicDto;
import com.mateocuevas.challenge.entity.Characteristic;
import com.mateocuevas.challenge.entity.Reading;

import java.util.List;

public interface CharacteristicService {
    void saveCharacteristicsForInitialization();
    void create (String name);
    List<CharacteristicDto> getAllCharacteristics();
    Characteristic addCharacteristicRandom(Reading reading);
}
