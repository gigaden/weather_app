package com.gigaden.weather_app.service;

import com.gigaden.weather_app.dto.SensorCreateDto;
import com.gigaden.weather_app.dto.SensorResponseDto;
import com.gigaden.weather_app.entity.Sensor;

public interface SensorService {

    SensorResponseDto createSensor(SensorCreateDto dto);

    Sensor getByName(String name);
}
