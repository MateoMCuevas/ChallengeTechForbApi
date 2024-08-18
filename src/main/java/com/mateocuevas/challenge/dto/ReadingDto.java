package com.mateocuevas.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReadingDto {
    private long okCount;
    private long mediumAlertCount;
    private long redAlertCount;
}
