package com.gigaden.weather_app.service;

import com.gigaden.weather_app.client.WeatherClient;
import com.gigaden.weather_app.dto.MeasurementCreateDto;
import com.gigaden.weather_app.dto.MeasurementResponseDto;
import com.gigaden.weather_app.dto.SensorCreateDto;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
public class WeatherClientService {

    private final WeatherClient weatherClient;
    private final int randomMeasurements = 1000;
    private final double minTemp = -30;
    private final double maxTemp = 30;

    public WeatherClientService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    /* Метод выполняет:
    - Регистрацию сенсора через `POST /sensors/registration`.
        - Отправляет 1000 измерений со случайными значениями температуры и осадков на `/measurements/add`.
        - Выполняет `GET /measurements`, чтобы получить все 1000 записанных измерений.
    */
    public Collection<MeasurementResponseDto> makeAndGetRandomMeasurements() {
        SensorCreateDto sensor =  weatherClient.createSensor(SensorCreateDto.builder().name("RandomSensor").build());

        Random rnd = new Random();

        for (int i = 0; i < randomMeasurements; i++) {
            double rndTemp = minTemp + (maxTemp - minTemp) * rnd.nextDouble();
            MeasurementCreateDto measurements = MeasurementCreateDto.builder()
                    .sensor(sensor)
                    .value(Math.round(rndTemp * 10.0) / 10.0)
                    .raining(rnd.nextBoolean())
                    .build();
            weatherClient.createMeasurement(measurements);
        }

        Collection<MeasurementResponseDto> measurementsResponse = weatherClient.getMeasurements();

        return measurementsResponse;
    }
}
