package com.codeshop.project.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.codeshop.project.exception.EntityNotFoundException;
import com.codeshop.project.exception.ErrorResponse;
import com.codeshop.project.exception.IncompleteCustomerException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler(IncompleteCustomerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIncompleteCustomerException(IncompleteCustomerException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(Exception exception) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

}