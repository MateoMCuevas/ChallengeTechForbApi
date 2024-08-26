package com.mateocuevas.challenge.service.characteristic;

import com.mateocuevas.challenge.dto.CharacteristicDto;
import com.mateocuevas.challenge.entity.Characteristic;
import com.mateocuevas.challenge.entity.Reading;
import com.mateocuevas.challenge.enums.Status;
import com.mateocuevas.challenge.repository.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CharacteristicServiceImpl implements CharacteristicService{

    @Autowired
    private CharacteristicRepository characteristicRepository;

    public void create (String name){
        Characteristic characteristic= Characteristic.builder()
                .name(name)
                .readings(new ArrayList<>())
                .build();
        characteristicRepository.save(characteristic);
    }
    public List<CharacteristicDto> getAllCharacteristics() {
        return characteristicRepository.findAll().stream()
                .map(characteristic -> CharacteristicDto.builder()
                        .name(characteristic.getName())
                        .okCount(getCountByStatus(characteristic,Status.LECTURAS_OK))
                        .mediumAlertCount(getCountByStatus(characteristic,Status.ALERTAS_MEDIAS))
                        .redAlertCount(getCountByStatus(characteristic,Status.ALERTAS_ROJAS))
                        .build())
                .collect(Collectors.toList());
    }

    private long getCountByStatus(Characteristic characteristic, Status status) {
        return characteristic.getReadings().stream()
                .filter(reading -> reading.getStatus() == status)
                .count();
    }

    private static final Random RANDOM = new Random();

    public Characteristic addCharacteristicRandom(Reading reading){
        List<Characteristic> characteristics = characteristicRepository.findAll();
        Characteristic randomCharacteristic = characteristics.get(RANDOM.nextInt(characteristics.size()));
        Optional<Characteristic> characteristicOptional = characteristicRepository.findByName(randomCharacteristic.getName());
        if (characteristicOptional.isPresent()) {
            Characteristic characteristic = characteristicOptional.get();
            System.out.println("error");
            characteristic.getReadings().add(reading);
            return characteristic;
        } else {
            throw new RuntimeException("Characteristic with name " + randomCharacteristic.getName() + " not found");
        }
    }

    public void saveCharacteristicsForInitialization() {
        List<Characteristic> characteristics = new ArrayList<>();
        characteristics.add(Characteristic.builder().name("Temperatura").build());
        characteristics.add(Characteristic.builder().name("Presión").build());
        characteristics.add(Characteristic.builder().name("Viento").build());
        characteristics.add(Characteristic.builder().name("Niveles").build());
        characteristics.add(Characteristic.builder().name("Energía").build());
        characteristics.add(Characteristic.builder().name("Tensión").build());
        characteristics.add(Characteristic.builder().name("Monóxido de carbono").build());
        characteristics.add(Characteristic.builder().name("Otros gases").build());
        for (Characteristic characteristic : characteristics) {
            if (!characteristicRepository.existsByName(characteristic.getName())) {
                characteristicRepository.save(characteristic);
            }
        }
    }
}
