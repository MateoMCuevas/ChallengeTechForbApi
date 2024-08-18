package com.mateocuevas.challenge.dto;

import com.mateocuevas.challenge.entity.Country;
import com.mateocuevas.challenge.entity.Reading;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlantDto {

    private String name;
    private Country country;
    private long okCount;
    private long mediumAlertCount;
    private long redAlertCount;
}
