package com.gigaden.weather_app.controller;

import com.gigaden.weather_app.dto.MeasurementCreateDto;
import com.gigaden.weather_app.dto.MeasurementResponseDto;
import com.gigaden.weather_app.service.MeasurementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    @Qualifier("measurementServiceImpl")
    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping
    public ResponseEntity<Collection<MeasurementResponseDto>> getAllMeasurements() {
        Collection<MeasurementResponseDto> measurements = measurementService.getAllMeasurements();

        return new ResponseEntity<>(measurements, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MeasurementResponseDto> addMeasurement(@Valid @RequestBody MeasurementCreateDto dto) {
        MeasurementResponseDto measurementResponseDto = measurementService.createMeasurement(dto);

        return new ResponseEntity<>(measurementResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Integer> getRainyDays() {
        Integer days = measurementService.getRainyDays();

        return new ResponseEntity<>(days, HttpStatus.OK);
    }



}
