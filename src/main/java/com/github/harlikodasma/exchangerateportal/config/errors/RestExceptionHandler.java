package com.github.harlikodasma.exchangerateportal.config.errors;

import com.github.harlikodasma.exchangerateportal.exceptions.PortalServiceRestClientException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PortalServiceRestClientException.class)
    public ResponseEntity<Object> restClientException(Exception ex) {
        var restError = new RestError(BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(restError, BAD_REQUEST);
    }
}
