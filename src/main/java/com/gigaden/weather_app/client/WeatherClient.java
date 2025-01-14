package com.gigaden.weather_app.client;

import com.gigaden.weather_app.dto.MeasurementCreateDto;
import com.gigaden.weather_app.dto.MeasurementResponseDto;
import com.gigaden.weather_app.dto.SensorCreateDto;
import com.gigaden.weather_app.exception.ClientRequestException;
import com.gigaden.weather_app.exception.ServerRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collection;


@Slf4j
@Service
public class WeatherClient {

    private final RestClient restClient;
    private static final String sensorReg = "/sensors/registration";
    private static final String measAdd = "/measurements/add";
    private static final String measGetAll = "/measurements";
    private static final String measGetAllRainy = "/measurements/rainyDaysCount";

    @Autowired
    public WeatherClient(@Value("${weather.server.url}") String baseUrl) {
        restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    // Отправляем запрос на добавление статистики
    public SensorCreateDto createSensor(SensorCreateDto sensorCreateDto) {
        log.info("Пытаюсь отправить запрос на добавление нового сенсора {}", sensorCreateDto);
        SensorCreateDto sensor = restClient.post()
                .uri(sensorReg)
                .contentType(MediaType.APPLICATION_JSON)
                .body(sensorCreateDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ClientRequestException(String.format("Ошибка клиента: %s %s", response.getStatusCode(),
                            response.getHeaders()));
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new ServerRequestException(String.format("Ошибка сервера: %s %s", response.getStatusCode(),
                            response.getHeaders()));
                })
                .body(SensorCreateDto.class);
        log.info("Запрос на добавление сенсора отправлен {}.", sensorCreateDto);

        return sensor;
    }

    // Отправляем запрос на добавление измерения
    public MeasurementCreateDto createMeasurement(MeasurementCreateDto measurementCreateDto) {
        log.info("Пытаюсь отправить запрос на добавление нового измерения {}", measurementCreateDto);
        MeasurementCreateDto measurement = restClient.post()
                .uri(measAdd)
                .contentType(MediaType.APPLICATION_JSON)
                .body(measurementCreateDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ClientRequestException(String.format("Ошибка клиента: %s %s", response.getStatusCode(),
                            response.getHeaders()));
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new ServerRequestException(String.format("Ошибка сервера: %s %s", response.getStatusCode(),
                            response.getHeaders()));
                })
                .body(MeasurementCreateDto.class);
        log.info("Запрос на добавление измерения отправлен {}.", measurementCreateDto);

        return measurement;
    }

    // Отправляем запрос на получение всех измерений
    public Collection<MeasurementResponseDto> getMeasurements() {
        log.info("Пытаюсь отправить запрос на получение всех измерения");

        Collection<MeasurementResponseDto> measurements = restClient.get().uri(uriBuilder -> uriBuilder
                        .path(measGetAll)
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ClientRequestException(String.format("Ошибка клиента: %s %s", response.getStatusCode(),
                            response.getHeaders()));
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new ServerRequestException(String.format("Ошибка сервера: %s %s", response.getStatusCode(),
                            response.getHeaders()));
                })
                .body(new ParameterizedTypeReference<>() {
                });
        log.info("Получены все измерения");

        return measurements;
    }

    // Отправляем запрос на получение всех дождливых дней
    public Integer getRainyDays() {
        log.info("Пытаюсь отправить запрос на получение всех дождливых дней");

        Integer days = restClient.get().uri(uriBuilder -> uriBuilder
                        .path(measGetAllRainy)
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ClientRequestException(String.format("Ошибка клиента: %s %s", response.getStatusCode(),
                            response.getHeaders()));
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new ServerRequestException(String.format("Ошибка сервера: %s %s", response.getStatusCode(),
                            response.getHeaders()));
                })
                .body(new ParameterizedTypeReference<>() {
                });
        log.info("Получены все дождливые дни");

        return days;
    }

}
