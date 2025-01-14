package com.gigaden.weather_app.controller;

import com.gigaden.weather_app.dto.MeasurementResponseDto;
import com.gigaden.weather_app.service.WeatherClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/randoms")
public class WeatherRandomController {

    private final WeatherClientService weatherClientService;

    @Autowired
    public WeatherRandomController(WeatherClientService weatherClientService) {
        this.weatherClientService = weatherClientService;
    }

    @GetMapping
    public ResponseEntity<Collection<MeasurementResponseDto>> getRandomMeauserements() {
        Collection<MeasurementResponseDto> measurements = weatherClientService.makeAndGetRandomMeasurements();

        return new ResponseEntity<>(measurements, HttpStatus.OK);
    }
}
