package com.gigaden.weather_app.controller;

import com.gigaden.weather_app.dto.MeasurementCreateDto;
import com.gigaden.weather_app.dto.MeasurementResponseDto;
import com.gigaden.weather_app.dto.SensorCreateDto;
import com.gigaden.weather_app.service.MeasurementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class MeasurementControllerTest {

    @Mock
    private MeasurementService measurementService;

    @InjectMocks
    private MeasurementController measurementController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Инициализация моков перед каждым тестом
    }

    @Test
    void testGetAllMeasurements() {
        // Создаем тестовые данные
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

        // Задаем поведение для мока
        when(measurementService.getAllMeasurements()).thenReturn(Arrays.asList(measurement1, measurement2));

        // Вызываем тестируемый метод
        ResponseEntity<Collection<MeasurementResponseDto>> response = measurementController.getAllMeasurements();

        // Проверяем результат
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(measurementService, times(1)).getAllMeasurements();
    }

    @Test
    void testAddMeasurement() {
        // Создаем тестовые данные
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

        // Задаем поведение для мока
        when(measurementService.createMeasurement(createDto)).thenReturn(responseDto);

        // Вызываем тестируемый метод
        ResponseEntity<MeasurementResponseDto> response = measurementController.addMeasurement(createDto);

        // Проверяем результат
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(20.0, response.getBody().getValue());
        assertTrue(response.getBody().isRaining());
        assertEquals(101L, response.getBody().getSensorId());
        verify(measurementService, times(1)).createMeasurement(createDto);
    }

    @Test
    void testGetRainyDays() {
        // Задаем поведение для мока
        when(measurementService.getRainyDays()).thenReturn(5);

        // Вызываем тестируемый метод
        ResponseEntity<Integer> response = measurementController.getRainyDays();

        // Проверяем результат
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5, response.getBody());
        verify(measurementService, times(1)).getRainyDays();
    }
}
