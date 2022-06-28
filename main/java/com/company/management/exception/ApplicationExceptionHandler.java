package com.company.management.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(er -> {
            errors.put(er.getField(), er.getDefaultMessage());
        });
        errors.put("HTTP METHOD ", status.value());
        errors.put("HTTP STATUS CODE ", HttpStatus.BAD_REQUEST);
        errors.put("URI-location ", request.getDescription(true));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported
            (HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("METHOD-USED ", ex.getMethod());
        errors.put("SUPPORTED METHOD ", ex.getSupportedHttpMethods());
        errors.put("HTTP STATUS CODE ", HttpStatus.METHOD_NOT_ALLOWED);
        errors.put("URI-location ", request.getDescription(true));
        return new ResponseEntity<>(errors, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFoundExceptions
            (EmployeeNotFoundException exception) {
        return new ResponseEntity<>
                (exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<Object> handleCompanyNotFoundExceptions
            (CompanyNotFoundException exception) {
        return new ResponseEntity<>
                (exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> genericExceptionHandler(Exception exception) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        error.setTimeStamp(LocalDateTime.now());
        error.setMessage(exception.getLocalizedMessage());
        Map<String, Object> errors = new HashMap<>();
        errors.put("Error occured at ", error.getTimeStamp());
        errors.put("HTTP STATUS CODE ", error.getStatus());
        errors.put("Reason ", error.getErrors());
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ConstraintViolationException.class,
            org.hibernate.exception.ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException
            (ConstraintViolationException exception) {
        ApiError error = new ApiError();
        error.setTimeStamp(LocalDateTime.now());
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage("sorry the something wrong is passed");
        Map<String, Object> errors = new HashMap<>();
        errors.put("Reason", error);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
