package com.gigaden.weather_app.service;

import com.gigaden.weather_app.dto.MeasurementCreateDto;
import com.gigaden.weather_app.dto.MeasurementResponseDto;

import java.util.Collection;

public interface MeasurementService {

    Collection<MeasurementResponseDto> getAllMeasurements();

    MeasurementResponseDto createMeasurement(MeasurementCreateDto dto);

    Integer getRainyDays();
}
