package com.exactorysolutions.dscatalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.exactorysolutions.dscatalog.services.exceptions.DatabaseException;
import com.exactorysolutions.dscatalog.services.exceptions.ResourceNotFoundException;

@ControllerAdvice //permite que a classe intercepte alguma excessão
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class) //para que o controlador saiba qual exceção ele deve interceptar
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found!");
		err.setMessage(e.getMessage()); //mensagem do orElseThrow
		err.setPath(request.getRequestURI()); //busca o caminho da requisição
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class) //para que o controlador saiba qual exceção ele deve interceptar
	public ResponseEntity<StandardError> entityNotFound(DatabaseException e, HttpServletRequest request) {
		/* Substituição por uma função de valorização de variáveis
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found!");
		err.setMessage(e.getMessage()); //mensagem do orElseThrow
		err.setPath(request.getRequestURI()); //busca o caminho da requisição
		*/
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return ResponseEntity.status(status).body(FillVariables(e.getMessage(), request.getRequestURI(), status));
	}
	
	private StandardError FillVariables(String msg, String getUri, HttpStatus status) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found!");
		err.setMessage(msg); //mensagem do orElseThrow
		err.setPath(getUri); //busca o caminho da requisição
		return err;
	}
}
