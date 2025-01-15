package com.gigaden.weather_app.controller;

import com.gigaden.weather_app.dto.MeasurementCreateDto;
import com.gigaden.weather_app.dto.MeasurementResponseDto;
import com.gigaden.weather_app.dto.SensorCreateDto;
import com.gigaden.weather_app.service.MeasurementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class MeasurementControllerTest {

    @Mock
    private MeasurementService measurementService;

    @InjectMocks
    private MeasurementController measurementController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetAllMeasurements() {
        MeasurementResponseDto measurement1 = MeasurementResponseDto.builder()
                .id(1L)
                .value(25.5)
                .raining(false)
                .sensorId(101L)
                .build();

        MeasurementResponseDto measurement2 = MeasurementResponseDto.builder()
                .id(2L)
                .value(18.0)
                .raining(true)
                .sensorId(102L)
                .build();

        when(measurementService.getAllMeasurements()).thenReturn(Arrays.asList(measurement1, measurement2));

        ResponseEntity<Collection<MeasurementResponseDto>> response = measurementController.getAllMeasurements();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(measurementService, times(1)).getAllMeasurements();
    }

    @Test
    void testAddMeasurement() {
        SensorCreateDto sensorDto = SensorCreateDto.builder()
                .name("Test Sensor")
                .build();

        MeasurementCreateDto createDto = MeasurementCreateDto.builder()
                .value(20.0)
                .raining(true)
                .sensor(sensorDto)
                .build();

        MeasurementResponseDto responseDto = MeasurementResponseDto.builder()
                .id(1L)
                .value(20.0)
                .raining(true)
                .sensorId(101L)
                .build();

        when(measurementService.createMeasurement(createDto)).thenReturn(responseDto);

        ResponseEntity<MeasurementResponseDto> response = measurementController.addMeasurement(createDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(20.0, response.getBody().getValue());
        assertEquals(true, response.getBody().isRaining());
        assertEquals(101L, response.getBody().getSensorId());
        verify(measurementService, times(1)).createMeasurement(createDto);
    }

    @Test
    void testGetRainyDays() {
        when(measurementService.getRainyDays()).thenReturn(5);

        ResponseEntity<Integer> response = measurementController.getRainyDays();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5, response.getBody());
        verify(measurementService, times(1)).getRainyDays();
    }
}
