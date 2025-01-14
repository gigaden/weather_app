package com.gigaden.weather_app.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleCategoryValidation(final SensorValidationException e, WebRequest request) {
        log.error("Ошибка 409 SensorValidationException: {} в запросе {}",
                e.getMessage(), request.getDescription(false));
        return buildErrorResponse(e, HttpStatus.CONFLICT, e.getReason());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e, WebRequest request) {
        log.error("Ошибка 400 MethodArgumentNotValidException: {} в запросе {}",
                e.getMessage(), request.getDescription(false));
        return buildErrorResponse(e, HttpStatus.CONFLICT, "Incorrectly made request.");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleSensorNotFound(final SensorNotFoundException e, WebRequest request) {
        log.error("Ошибка 404 NotFoundException: {} в запросе {}",
                e.getMessage(), request.getDescription(false));
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, e.getReason());
    }

    public Map<String, String> buildErrorResponse(Exception e, HttpStatus status, String reason) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("status", status.name());
        response.put("reason", reason);
        response.put("message", e.getMessage());
        response.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return response;
    }


}