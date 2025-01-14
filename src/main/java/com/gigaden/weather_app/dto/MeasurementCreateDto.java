package com.gigaden.weather_app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasurementCreateDto {

    @NotNull
    private Double value;

    @NotNull
    private Boolean raining;

    @NotNull
    private SensorCreateDto sensor;
}
