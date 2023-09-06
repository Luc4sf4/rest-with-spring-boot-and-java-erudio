package br.com.erudio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RequeiredObjectIsNullException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public RequeiredObjectIsNullException() {
		
		super("It's not allowad to persisted a null object");
		
	}
	
	
	public RequeiredObjectIsNullException(String ex) {
		
		super(ex);
		
	}
	
	

	
	
	

}
