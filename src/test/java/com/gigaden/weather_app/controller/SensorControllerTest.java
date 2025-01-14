package com.gigaden.weather_app.controller;

import com.gigaden.weather_app.dto.SensorCreateDto;
import com.gigaden.weather_app.dto.SensorResponseDto;
import com.gigaden.weather_app.service.SensorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SensorControllerTest {

    @Mock
    private SensorService sensorService;

    @InjectMocks
    private SensorController sensorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Инициализация моков перед каждым тестом
    }

    @Test
    void testAddSensor() {
        // Создаем тестовые данные
        SensorCreateDto createDto = SensorCreateDto.builder()
                .name("Test Sensor")
                .build();

        LocalDateTime now = LocalDateTime.now();
        SensorResponseDto responseDto = SensorResponseDto.builder()
                .id(1L)
                .name("Test Sensor")
                .createdOn(now)
                .build();

        // Задаем поведение для мока
        when(sensorService.createSensor(createDto)).thenReturn(responseDto);

        // Вызываем тестируемый метод
        ResponseEntity<SensorResponseDto> response = sensorController.addSensor(createDto);

        // Проверяем результат
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Test Sensor", response.getBody().getName());
        assertEquals(now, response.getBody().getCreatedOn());
        verify(sensorService, times(1)).createSensor(createDto);
    }
}
