package com.mateocuevas.challenge.service.characteristic;

import com.mateocuevas.challenge.dto.CharacteristicDto;
import com.mateocuevas.challenge.entity.Characteristic;
import com.mateocuevas.challenge.entity.Reading;
import com.mateocuevas.challenge.enums.Status;
import com.mateocuevas.challenge.repository.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CharacteristicServiceImpl implements CharacteristicService{

    @Autowired
    private CharacteristicRepository characteristicRepository;

    public List<CharacteristicDto> getCharacteristicsWithAlerts() {
        return characteristicRepository.findAll().stream()
                .map(characteristic -> CharacteristicDto.builder()
                        .name(characteristic.getName())
                        .okCount(getCountByStatus(characteristic,Status.OK))
                        .mediumAlertCount(getCountByStatus(characteristic,Status.ALERTA_MEDIANA))
                        .redAlertCount(getCountByStatus(characteristic,Status.ALERTA_ROJA))
                        .build())
                .collect(Collectors.toList());
    }

    private long getCountByStatus(Characteristic characteristic, Status status) {
        return characteristic.getReadings().stream()
                .filter(reading -> reading.getStatus() == status)
                .count();
    }
}
