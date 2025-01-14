package com.gigaden.weather_app.mapper;

import com.gigaden.weather_app.dto.MeasurementCreateDto;
import com.gigaden.weather_app.dto.MeasurementResponseDto;
import com.gigaden.weather_app.entity.Measurement;
import com.gigaden.weather_app.entity.Sensor;

import java.time.LocalDateTime;

public class MeasurementMapper {

    public static MeasurementResponseDto mapMeasurementToDto(Measurement measurement) {
        return MeasurementResponseDto.builder()
                .id(measurement.getId())
                .value(measurement.getValue())
                .raining(measurement.isRaining())
                .sensorId(measurement.getSensor().getId())
                .build();
    }

    public static Measurement mapDtoToMeasurement(MeasurementCreateDto dto, Sensor sensor) {
        return Measurement.builder()
                .value(dto.getValue())
                .raining(dto.getRaining())
                .sensor(sensor)
                .createdOn(LocalDateTime.now())
                .build();
    }
}
