package com.gigaden.weather_app.controller;

import com.gigaden.weather_app.dto.SensorCreateDto;
import com.gigaden.weather_app.dto.SensorResponseDto;
import com.gigaden.weather_app.service.SensorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    @Qualifier("sensorServiceImpl")
    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/registration")
    public ResponseEntity<SensorResponseDto> addSensor(@Valid @RequestBody SensorCreateDto dto) {
        SensorResponseDto sensorResponseDto = sensorService.createSensor(dto);
        return new ResponseEntity<>(sensorResponseDto, HttpStatus.CREATED);
    }
}
