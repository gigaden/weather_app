package com.gigaden.weather_app.repository;

import com.gigaden.weather_app.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    boolean existsByName(String name);
}
