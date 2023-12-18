package com.ericksena.backendbarbeariatrainee.api.exceptionhandler;

import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = ex.getConstraintViolations().stream().findFirst().get().getMessage();
        ErrorMessage errorMessage = instantiateErrorMassage(status, message);
        
        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        HttpStatus status = statusCodeToHttpStatus(ex.getStatusCode());
        ErrorMessage errorMessage = instantiateErrorMassage(status, ex.getReason());

        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
    }

    private final HttpStatus statusCodeToHttpStatus(HttpStatusCode statusCode) {
        return HttpStatus.valueOf(statusCode.value());
    }

    private final ErrorMessage instantiateErrorMassage(HttpStatus status, String reason) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(status.value());
        errorMessage.setDateTime(OffsetDateTime.now());
        errorMessage.setReason(reason);
        return errorMessage;
    }
}
