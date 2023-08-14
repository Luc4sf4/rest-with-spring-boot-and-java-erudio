package br.com.erudio;

import org.springframework.stereotype.Service;

import br.com.erudio.exception.UnsupportedMathOperationException;

@Service
public class MathService {

	public Double sum(Double numberOne, Double numberTwo) {
		
		   if (numberOne == null || numberTwo == null) {
	            throw new UnsupportedMathOperationException("Please set a numeric value");
	        }
		
		return numberOne + numberTwo;
		
	}
	
	public Double subtract(Double numberOne, Double numberTwo) {
		
		  if (numberOne == null || numberTwo == null) {
	            throw new UnsupportedMathOperationException("Please set a numeric value");
	        }
		
		return numberOne - numberTwo;
		
	}
	
	public Double multiply(Double numberOne, Double numberTwo) {
		
		  if (numberOne == null || numberTwo == null) {
	            throw new UnsupportedMathOperationException("Please set a numeric value");
	        }
		
		return numberOne * numberTwo;
		
	}
	
	public Double divide(Double numberOne, Double numberTwo) {
		
		
		return numberOne / numberTwo;
		
	}
	
	public Double mean (Double numberOne, Double numberTwo) {
		
		 if (numberTwo == 0) {
	            throw new ArithmeticException("Cannot divide by zero");
	        }
		
		return (numberOne + numberTwo) /2;
		
	}
	
	public Double squareRoot(Double number) {
		
		if (number < 0) {
            throw new UnsupportedMathOperationException("Cannot calculate square root of a negative number");
        }
		
		
		return Math.sqrt(number);
		
	}
	
	
}
