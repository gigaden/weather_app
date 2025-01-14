package com.gigaden.weather_app.repository;

import com.gigaden.weather_app.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    Integer countAllByRaining(Boolean rain);
}
