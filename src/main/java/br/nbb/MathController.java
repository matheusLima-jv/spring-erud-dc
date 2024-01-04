package br.nbb;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.nbb.complements.Calculos;
import br.nbb.exception.MathOpExcep;

@RestController
public class MathController {

    private final AtomicLong count = new AtomicLong();
    
    Calculos calc = new Calculos();
    
    @RequestMapping (value = "/{val}/{numberOne}/{numberTow}", method = RequestMethod.GET)
    public Double calc(@PathVariable(value = "numberOne") String numberOne,
                       @PathVariable(value = "numberTow") String numberTow,
                       @PathVariable(value = "val") String val) throws MathOpExcep {

        if (!calc.isNumb(numberOne) || !calc.isNumb(numberTow)) {
            throw new MathOpExcep("Informe um valor numerico valido");
        }

        Double result = null;

        switch (val) {
            case "soma":
                result = calc.convertDb(numberOne) + calc.convertDb(numberTow);
                break;
            case "sub":
                result = calc.convertDb(numberOne) - calc.convertDb(numberTow);
                break;
            case "mult":
                result = calc.convertDb(numberOne) * calc.convertDb(numberTow);
                break;
            case "divi":
                result = calc.convertDb(numberOne) / calc.convertDb(numberTow);
                break;
            case "med":
                result = (calc.convertDb(numberOne) + calc.convertDb(numberTow)) / 2;
                break;
            default:
                throw new MathOpExcep("Operação matemática inválida");
        }

        return result;
    }
    
    @RequestMapping(value="/raiz/{number}", method = RequestMethod.GET)
    	public Double raiz(@PathVariable (value = "number")String number) {
    	
    	if (!calc.isNumb(number)) {
            throw new MathOpExcep("Informe um valor numerico valido");
        }
    	
    	return Math.sqrt(calc.convertDb(number));
    }
    
}
