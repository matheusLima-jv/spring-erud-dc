package br.nbb.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class JwtTokenFilter extends GenericFilterBean{
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	public JwtTokenFilter(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	//obtem o token a partir da request depois valida o token e obtem a autenticação e set a autenticação a seção do spring
	@Override //filtro executado a cada requisição 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String token = tokenProvider.resolveToken((HttpServletRequest) request);
		if (token != null && tokenProvider.validateToken(token)) {
			Authentication ath = tokenProvider.getAutentication(token); 
			
			if (ath != null) {
				SecurityContextHolder.getContext().setAuthentication(ath);
			}
		}
		chain.doFilter(request, response);
	}
	/*
	 * a classe JwtTokenFilter é um filtro que intercepta cada requisição HTTP para validar um token JWT. 
	 * Se o token for válido, ele obtém a autenticação correspondente e a configura no contexto de 
	 * segurança do Spring Security.
	 */
	

}
