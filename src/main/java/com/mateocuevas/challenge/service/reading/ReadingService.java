package com.mateocuevas.challenge.service.reading;

import com.mateocuevas.challenge.dto.ReadingDto;
import com.mateocuevas.challenge.entity.Reading;
import com.mateocuevas.challenge.enums.Status;

import java.util.List;

public interface ReadingService {
    ReadingDto getReadingsByStatus();
}
