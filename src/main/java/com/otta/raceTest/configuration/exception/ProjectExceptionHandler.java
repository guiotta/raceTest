package com.otta.raceTest.configuration.exception;

import java.text.ParseException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Classe para tratar as exceções do projeto.
 * 
 * @author Guilherme
 *
 */
@ControllerAdvice
public class ProjectExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleIllegalArgumebts(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = String.format("Could not complete your request: %s", ex.getMessage());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = { ParseException.class })
	protected ResponseEntity<Object> handleParseException(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = String.format("Could not parse selected file: %s", ex.getMessage());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler(value = { NumberFormatException.class })
	protected ResponseEntity<Object> handleNumberFormatException(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = String.format("Could not parse element in selected file: %s", ex.getMessage());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
