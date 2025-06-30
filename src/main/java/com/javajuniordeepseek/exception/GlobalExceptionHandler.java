package com.javajuniordeepseek.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ClienteNaoEncontradoException.class) /*será executado sempre que a exceção Cli-
	enteNotFoundException for lançada em qualquer controller.*/
    public ResponseEntity<String> handleClienteNaoEncontrado(ClienteNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

	@ExceptionHandler(ArgumentoInvalidoException.class)
    public ResponseEntity<String> handleArgumentoInvalidoException(ArgumentoInvalidoException ex)
	{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
		
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<String> handleSpringMethodArgumentNotValidException(
	            MethodArgumentNotValidException ex) {
	        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
	                .map(error -> error.getField() + ": " + error.getDefaultMessage())
	                .collect(Collectors.joining("; "));
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erros de validação: " + errorMessage);
	    }
}
