package com.gigaden.weather_app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MeasurementResponseDto {

    private long id;

    private double value;

    private boolean raining;

    private long sensorId;

}
