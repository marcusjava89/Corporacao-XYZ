package com.javajuniordeepseek.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.javajuniordeepseek.exception.ClienteNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(ClienteNotFoundException.class)
	    public ResponseEntity<String> handleClienteNotFoundException(ClienteNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
}
