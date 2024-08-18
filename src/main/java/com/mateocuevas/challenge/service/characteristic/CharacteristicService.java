package com.mateocuevas.challenge.service.characteristic;

import com.mateocuevas.challenge.dto.CharacteristicDto;
import com.mateocuevas.challenge.entity.Characteristic;
import com.mateocuevas.challenge.entity.Reading;
import com.mateocuevas.challenge.enums.Status;

import java.util.List;
import java.util.Map;

public interface CharacteristicService {
    List<CharacteristicDto> getCharacteristicsWithAlerts();
}
