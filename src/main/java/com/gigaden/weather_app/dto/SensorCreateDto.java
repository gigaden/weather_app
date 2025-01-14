package com.gigaden.weather_app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SensorCreateDto {

    @NotBlank
    private String name;
}
