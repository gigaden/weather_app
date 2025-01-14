package com.gigaden.weather_app.exception;

import lombok.Getter;

@Getter
public class ServerRequestException extends RuntimeException {

    private final String reason = "Server error";

    public ServerRequestException(String message) {
        super(message);
    }
}