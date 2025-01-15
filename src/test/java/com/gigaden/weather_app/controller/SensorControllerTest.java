package com.gigaden.weather_app.controller;

import com.gigaden.weather_app.dto.SensorCreateDto;
import com.gigaden.weather_app.dto.SensorResponseDto;
import com.gigaden.weather_app.service.SensorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class SensorControllerTest {

    @Mock
    private SensorService sensorService;

    @InjectMocks
    private SensorController sensorController;

    @Test
    void testAddSensor() {
        SensorCreateDto createDto = SensorCreateDto.builder()
                .name("Test Sensor")
                .build();

        LocalDateTime now = LocalDateTime.now();
        SensorResponseDto responseDto = SensorResponseDto.builder()
                .id(1L)
                .name("Test Sensor")
                .createdOn(now)
                .build();

        when(sensorService.createSensor(createDto)).thenReturn(responseDto);

        ResponseEntity<SensorResponseDto> response = sensorController.addSensor(createDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Test Sensor", response.getBody().getName());
        assertEquals(now, response.getBody().getCreatedOn());
        verify(sensorService, times(1)).createSensor(createDto);
    }
}
