package com.gigaden.weather_app.service;

import com.gigaden.weather_app.dto.SensorCreateDto;
import com.gigaden.weather_app.dto.SensorResponseDto;

public interface SensorService {

    SensorResponseDto createSensor(SensorCreateDto dto);
}
