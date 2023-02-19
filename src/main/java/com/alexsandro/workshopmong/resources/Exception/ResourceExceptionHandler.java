package com.alexsandro.workshopmong.resources.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alexsandro.workshopmong.services.exception.ObjectNotFondException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFondException.class)
	public ResponseEntity<EstandardError> objectNotFound(ObjectNotFondException e, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		EstandardError err = new EstandardError(System.currentTimeMillis(), status.value(), "Not found", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
