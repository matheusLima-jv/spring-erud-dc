package br.nbb.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.nbb.exception.ExceptionResponse;
import br.nbb.exception.MathOpExcep;

@ControllerAdvice
@RestController
public class CustonRespEntiExcepHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExep(Exception ex, WebRequest req){
		
		ExceptionResponse exp = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		
		return new ResponseEntity<>(exp, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	
	@ExceptionHandler(MathOpExcep.class)
	public final ResponseEntity<ExceptionResponse> handleBadReqExep(Exception ex, WebRequest req){
		
		ExceptionResponse exp = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		
		return new ResponseEntity<>(exp, HttpStatus.BAD_REQUEST);
			
		}

}
