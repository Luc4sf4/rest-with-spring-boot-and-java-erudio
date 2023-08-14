package br.com.erudio;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exception.UnsupportedMathOperationException;

@RestController
@RequestMapping("/math")
public class MathController {
	
	
	private final MathService mathService;
	
    public MathController(MathService mathService) {
        this.mathService = mathService;
    }
	
    @GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(
			@PathVariable Double numberOne,
			@PathVariable Double  numberTwo)throws Exception {
		
		
	return mathService.sum(numberOne, numberTwo);
	}

	@GetMapping(value ="/multiply/{numberOne}/{numberTwo}")
	public Double multiply(
			@PathVariable Double numberOne,
			@PathVariable Double numberTwo)throws Exception {
		
	return mathService.multiply(numberOne, numberTwo);
	}
	
	@GetMapping(value ="/subtract/{numberOne}/{numberTwo}")
	public Double subtract(
			@PathVariable Double numberOne,
			@PathVariable Double numberTwo)throws Exception {
		
		
	return mathService.subtract(numberOne, numberTwo);
	}
	
	@GetMapping(value ="/divide/{numberOne}/{numberTwo}")
	public Double divide(
			@PathVariable Double numberOne,
			@PathVariable Double numberTwo)throws Exception {
		
		
	return mathService.divide(numberOne, numberTwo);
	}
	
	@GetMapping(value ="/sqrt/{numberOne}")
	public Double squareRoot(
			@PathVariable Double numberOne)throws Exception {
		
		
	 return mathService.squareRoot(numberOne);
	}
	
	@RequestMapping(value ="/mean/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double mean(
			@PathVariable Double numberOne,
			@PathVariable Double numberTwo)throws Exception {
		
		
	return mathService.mean(numberOne, numberTwo);
	}
	
	
	
}
