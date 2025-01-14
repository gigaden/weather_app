package com.gigaden.weather_app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class SensorCreateDto {

    @NotBlank
    private String name;
}
