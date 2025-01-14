package com.gigaden.weather_app.repository;

import com.gigaden.weather_app.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    boolean existsByName(String name);

    Optional<Sensor> findSensorByName(String name);
}
