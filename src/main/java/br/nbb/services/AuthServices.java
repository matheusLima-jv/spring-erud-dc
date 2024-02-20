package br.nbb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.nbb.dataVO.v1.security.AccountCredentialsVO;
import br.nbb.dataVO.v1.security.TokenVO;
import br.nbb.repositories.UserRepository;
import br.nbb.security.jwt.JwtTokenProvider;

@Service
public class AuthServices {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity singin(AccountCredentialsVO credential) {
		try {
			var username = credential.getUsername();
			var password = credential.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
			var user = userRepository.findByUserName(username);
			
			var tokenResponse = new TokenVO();
			if (user != null) {
				tokenResponse = tokenProvider.createAcessTok(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Username " + username + " Not found!!! ");
			}
			
			return ResponseEntity.ok(tokenResponse);
			
		} catch (Exception e) {
			throw new BadCredentialsException(" Invalid Username/password !!! ");
		}
		
	}
	

}
