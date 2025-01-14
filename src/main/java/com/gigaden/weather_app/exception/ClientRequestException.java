package com.gigaden.weather_app.exception;

import lombok.Getter;

@Getter
public class ClientRequestException extends RuntimeException {

    private final String reason = "Client error";

    public ClientRequestException(String message) {
        super(message);
    }
}