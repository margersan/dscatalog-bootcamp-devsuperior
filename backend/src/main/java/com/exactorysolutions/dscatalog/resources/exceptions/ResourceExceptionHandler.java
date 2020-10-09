package com.exactorysolutions.dscatalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.exactorysolutions.dscatalog.services.exceptions.EntityNotFoundException;

@ControllerAdvice //permite que a classe intercepte alguma excessão
public class ResourceExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class) //para que o controlador saiba qual exceção ele deve interceptar
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Resource not found!");
		err.setMessage(e.getMessage()); //mensagem do orElseThrow
		err.setPath(request.getRequestURI()); //busca o caminho da requisição
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
}
