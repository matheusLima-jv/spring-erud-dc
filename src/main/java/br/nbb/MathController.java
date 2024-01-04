package br.nbb;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
	
	private final AtomicLong count = new AtomicLong();
	
	@GetMapping("/soma/{numberOne}/{numberTow}")
	public Double calc(@PathVariable(value ="numberOne")String numberOne,
					@PathVariable(value ="numberTow")String numberTow ) throws Exception {	
		
		if(!isNumb(numberOne) || !isNumb(numberTow)) {
			
			throw new Exception();
	}
		
		return convertDb(numberOne) + convertDb(numberTow);
	
	}

	private boolean isNumb(String str) {
		if( str == null) return false;
		String strNumber = str.replaceAll(",", ".");
		return strNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

	private Double convertDb(String str) {
		if( str == null) return 0D;
		String strNumber = str.replaceAll(",", ".");
		if(isNumb(strNumber)) return Double.parseDouble(strNumber);
		return 0D;
	}
	
	

}
