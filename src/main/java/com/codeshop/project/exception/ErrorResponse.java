package com.codeshop.project.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorResponse {
    private String status;
    private String message;
    private String timestamp;

    private ErrorResponse() {
        timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this();
        this.message = message;
        this.status = httpStatus.name();
    }

    public ErrorResponse(HttpStatus httpStatus, Throwable ex) {
        this();
        this.message = ex.getLocalizedMessage();
        this.status = httpStatus.name();
    }

}
