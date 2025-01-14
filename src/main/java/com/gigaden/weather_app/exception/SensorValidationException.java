package com.gigaden.weather_app.exception;

import lombok.Getter;

@Getter
public class SensorValidationException extends RuntimeException {

    private final String reason = "For the requested operation the conditions are not met.";

    public SensorValidationException(String message) {
        super(message);
    }
}