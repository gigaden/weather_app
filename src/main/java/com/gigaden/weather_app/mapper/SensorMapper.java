package com.gigaden.weather_app.mapper;

import com.gigaden.weather_app.dto.SensorCreateDto;
import com.gigaden.weather_app.dto.SensorResponseDto;
import com.gigaden.weather_app.entity.Sensor;

import java.time.LocalDateTime;

public class SensorMapper {

    public static Sensor mapToSensorFromDto(SensorCreateDto dto) {
        return Sensor.builder()
                .name(dto.getName())
                .createdOn(LocalDateTime.now())
                .build();
    }

    public static SensorResponseDto mapToResponseDtoFromSensor(Sensor sensor) {
        return SensorResponseDto.builder()
                .id(sensor.getId())
                .name(sensor.getName())
                .createdOn(sensor.getCreatedOn())
                .build();
    }
}
