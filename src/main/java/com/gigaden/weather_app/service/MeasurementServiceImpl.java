package com.gigaden.weather_app.service;

import com.gigaden.weather_app.dto.MeasurementCreateDto;
import com.gigaden.weather_app.dto.MeasurementResponseDto;
import com.gigaden.weather_app.entity.Measurement;
import com.gigaden.weather_app.entity.Sensor;
import com.gigaden.weather_app.mapper.MeasurementMapper;
import com.gigaden.weather_app.repository.MeasurementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service("measurementServiceImpl")
@Transactional(readOnly = true)
@Slf4j
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementServiceImpl(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Override
    public Collection<MeasurementResponseDto> getAllMeasurements() {
        log.info("Пытаюсь получить коллекцию всех измерений");
        Collection<Measurement> measurements = measurementRepository.findAll();
        log.info("Коллекция всех измерений получена");

        return measurements.stream().map(MeasurementMapper::mapMeasurementToDto).toList();
    }

    @Override
    @Transactional
    public MeasurementResponseDto createMeasurement(MeasurementCreateDto dto) {
        log.info("Пытаюсь добавить новое измерение {}", dto);
        Sensor sensor = sensorService.getByName(dto.getSensor().getName());
        Measurement measurement = measurementRepository.save(MeasurementMapper.mapDtoToMeasurement(dto, sensor));
        log.info("Измерение с id = {} добавлено", measurement.getId());

        return MeasurementMapper.mapMeasurementToDto(measurement);
    }

    @Override
    public Integer getRainyDays() {
        log.info("Пытаюсь получить кол-во дождливых дней");
        Integer days = measurementRepository.countAllByRaining(true);
        log.info("Кол-во дождливых дней получено");

        return days;
    }
}
