package com.mateocuevas.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlantDto {

    private Long id;
    private String name;
    private String country;
    private long okCount;
    private long mediumAlertCount;
    private long redAlertCount;
}
