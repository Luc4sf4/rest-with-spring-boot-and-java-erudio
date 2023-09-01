package br.com.erudio.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.erudio.exception.ExceptionsResponse;
import br.com.erudio.exception.RequeiredObjectIsNullException;
import br.com.erudio.exception.ResourceNotFoundException;

@ControllerAdvice //use to concetrate a treatment that is spread in every Controllers
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionsResponse> handleAllException (
			Exception ex, WebRequest request) {
		
		ExceptionsResponse exceptionResponse = new ExceptionsResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<> (exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionsResponse> handleNotFoundException (
			Exception ex, WebRequest request) {
		
		ExceptionsResponse exceptionResponse = new ExceptionsResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<> (exceptionResponse,HttpStatus.NOT_FOUND);
		
		
	}
	
	@ExceptionHandler(RequeiredObjectIsNullException.class)
	public final ResponseEntity<ExceptionsResponse> handleBadRequestException (
			Exception ex, WebRequest request) {
		
		ExceptionsResponse exceptionResponse = new ExceptionsResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<> (exceptionResponse,HttpStatus.BAD_REQUEST);
		
		
	}
	

}
