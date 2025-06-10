package com.javajuniordeepseek.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.javajuniordeepseek.exception.ClienteNotFoundException;

@RestControllerAdvice //Essa anotação faz com que a classe intercepte exceções lançadas por qualquer @RestController do seu projeto.
public class GlobalExceptionHandler {
	 @ExceptionHandler(ClienteNotFoundException.class) //será executado sempre que a exceção ClienteNotFoundException for lançada em
	 //qualquer controller.*/
	    public ResponseEntity<String> handleClienteNotFoundException(ClienteNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
}
