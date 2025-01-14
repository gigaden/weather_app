package com.gigaden.weather_app.service;

import com.gigaden.weather_app.dto.SensorCreateDto;
import com.gigaden.weather_app.dto.SensorResponseDto;
import com.gigaden.weather_app.entity.Sensor;
import com.gigaden.weather_app.exception.SensorValidationException;
import com.gigaden.weather_app.mapper.SensorMapper;
import com.gigaden.weather_app.repository.SensorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sensorServiceImpl")
@Transactional
@Slf4j
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public SensorResponseDto createSensor(SensorCreateDto dto) {
        log.info("Пытаюсь добавить новый сенсор {}", dto);
        validateSensor(dto);
        Sensor sensor = SensorMapper.mapToSensorFromDto(dto);
        sensorRepository.save(sensor);
        log.info("Новый сенсор добавлен {}", sensor);
        return SensorMapper.mapToResponseDtoFromSensor(sensor);
    }

    // Чекаем имя сенсора. По идее, лучше сделать отдельный класс Validator и туда вынести проверки,
    // но, раз проверок мало, ограничимся методом в сервисе
    public void validateSensor(SensorCreateDto dto) {
        if (sensorRepository.existsByName(dto.getName())) {
            log.warn("Попытка добавить сенсор с существующим именем {}", dto.getName());
            throw new SensorValidationException("Сенсор с таким именем уже существует");
        }
    }
}
