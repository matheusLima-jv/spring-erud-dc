package br.nbb.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.nbb.dataVO.v1.security.AccountCredentialsVO;
import br.nbb.services.AuthServices;

//@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthServices authServices;
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/signin")
	public ResponseEntity logando(@RequestBody AccountCredentialsVO acVO) {
		if (checkIfParamsIsNull(acVO)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Requesttss !!!");
		
			var token = authServices.singin(acVO);
		
			if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Requesttss !!!");			
				
			return token;
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping(value = "/refresh/{username}")
	public ResponseEntity refreshTk(@PathVariable("username") String username,@RequestHeader("Authorization") String refresh) {
		if (checkIfParamsIsNullrf(username,refresh)) 
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Requesttss !!!");
		
			var token = authServices.refreshToken(username, refresh);
			if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Requesttss !!!");			
			return token;
	}

	private Boolean checkIfParamsIsNullrf(String username, String refresh) {
		return refresh == null || refresh.isBlank() || username == null || username.isBlank();
		
	}
	
	private Boolean checkIfParamsIsNull(AccountCredentialsVO acVO) {
		return acVO == null || acVO.getUsername() == null || acVO.getUsername().isBlank()
				|| acVO.getPassword() == null || acVO.getPassword().isBlank();
		
	}
	
}
