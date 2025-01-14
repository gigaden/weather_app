package com.gigaden.weather_app.exception;

import lombok.Getter;

@Getter
public class SensorNotFoundException extends RuntimeException {

    private final String reason = "The required object was not found.";

    public SensorNotFoundException(String message) {
        super(message);
    }
}