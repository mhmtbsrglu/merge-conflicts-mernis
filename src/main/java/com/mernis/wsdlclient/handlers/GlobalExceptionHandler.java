package com.mernis.wsdlclient.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleRuntimeException(RuntimeException exception){
        Map<String,Object> response = new HashMap<>();
        response.put("message",exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception){
        Map<String,Object> response = new HashMap<>();
        response.put("message","Content-type required.");
        response.put("listOfContentTypes", Arrays.asList("application/xml","application/json"));
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
