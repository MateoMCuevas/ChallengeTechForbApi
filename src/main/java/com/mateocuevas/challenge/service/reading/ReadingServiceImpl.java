package com.mateocuevas.challenge.service.reading;

import com.mateocuevas.challenge.dto.ReadingDto;
import com.mateocuevas.challenge.entity.Reading;
import com.mateocuevas.challenge.enums.Status;
import com.mateocuevas.challenge.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingServiceImpl implements ReadingService{
    @Autowired
    private ReadingRepository readingRepository;

    public ReadingDto getReadingsByStatus() {
        long okCount = readingRepository.countByStatus(Status.OK);
        long mediumAlertCount = readingRepository.countByStatus(Status.ALERTA_MEDIANA);
        long redAlertCount = readingRepository.countByStatus(Status.ALERTA_ROJA);
        return ReadingDto.builder()
                .okCount(okCount)
                .mediumAlertCount(mediumAlertCount)
                .redAlertCount(redAlertCount)
                .build();
    }
}
