package com.shivansh.SprinBoot_CRUD.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationExceptionResponseDto {

    private LocalDateTime timeStamp;
    private int statusCode;
    private String error;
    private String message;
    private Map<String,String> errorField;

    public ValidationExceptionResponseDto(LocalDateTime timeStamp, int statusCode, String error,
                                          String message, Map<String, String> errorField) {
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.errorField = errorField;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrorField() {
        return errorField;
    }

    public void setErrorField(Map<String, String> errorField) {
        this.errorField = errorField;
    }
}
