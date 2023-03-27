package com.fourheads.bookstore.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fourheads.bookstore.service.exceptions.DataIntegrityViolationException;
import com.fourheads.bookstore.service.exceptions.ObjectNotFoundException;
import com.fourheads.bookstore.service.exceptions.StanderError;

import jakarta.servlet.ServletRequest;

@ControllerAdvice
public class ResourceExceptionHandle {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StanderError> objectoNotFoundException(ObjectNotFoundException obj, ServletRequest request){
		StanderError error = new StanderError(
				System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value(),
				obj.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StanderError> dataIntegrityViolationException(DataIntegrityViolationException obj, ServletRequest request){
		StanderError error = new StanderError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				obj.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
