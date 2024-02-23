package br.nbb.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.nbb.dataVO.v1.security.TokenVO;
import br.nbb.exception.InvalidJwtAutExcept;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtTokenProvider {
	
	@Value("${security:jwt:token:secret-key:secret}")
	private String secretKey = "secret";
	
	@Value("3600000")
	private long valInMilliseconds = 3600000; //igual a uma hora
	
	@Autowired
	private UserDetailsService userDTService;
	
	Algorithm algorithm = null;
	
	@PostConstruct //executa o processo na inicialização do spring porem antes de ação do usuário
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes()); // pega oque foi setado na variavel encripta e seta novamente ( mudando o valor)
		algorithm = Algorithm.HMAC256(secretKey.getBytes());
		
	}
	
	Date now = new Date();
	Date validity = new Date(now.getTime() + valInMilliseconds);
	
	public TokenVO createAcessTok(String username, List<String> roles) {
		var acessToken = getAcessToken(username, roles, now, validity);
		var refreshToken = refReshToken(username, roles, now);
		
		return new TokenVO(username,true,now,validity,acessToken,refreshToken);
		
	}
	
	public TokenVO refreshTok(String refreshToken) {
		if(refreshToken.contains("Bearer ")) refreshToken = refreshToken.substring("Bearer ".length());
		
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT decodedJWT = verifier.verify(refreshToken);
		String username = decodedJWT.getSubject();
		List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
		return createAcessTok(username, roles);
		
	}


	private String refReshToken(String username, List<String> roles, Date now) {
		Date validityReshToken = new Date(now.getTime() + (valInMilliseconds * 3));      
		return JWT.create().withClaim("roles", roles)
				.withIssuedAt(now).withExpiresAt(validityReshToken)
				.withSubject(username).sign(algorithm);
	}

	private String getAcessToken(String username, List<String> roles, Date now, Date validity) {
		String isrUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString(); // pega a url do servidor 
		return JWT.create().withClaim("roles", roles)
				.withIssuedAt(now).withExpiresAt(validity)
				.withSubject(username).withIssuer(isrUrl).sign(algorithm);
	}
	
	public Authentication getAutentication(String token) {
		DecodedJWT decodedJWT = decodedToken(token);
		UserDetails userDT = this.userDTService.loadUserByUsername(decodedJWT.getSubject());
		return new UsernamePasswordAuthenticationToken(userDT, "",userDT.getAuthorities());
	}


	private DecodedJWT decodedToken(String token) {
		Algorithm algorithmDecoToken = Algorithm.HMAC256(secretKey.getBytes());
		JWTVerifier verifier = JWT.require(algorithmDecoToken).build();
		DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT;
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if ( bearerToken != null && bearerToken.startsWith("Bearer ")) {
			
			return bearerToken.substring("Bearer ".length());
		}
		return null;
	}
	
	public Boolean validateToken(String token) {
		
		DecodedJWT decodedJWT = decodedToken(token);
		try {
			if (decodedJWT.getExpiresAt().before(new Date())) {
				
				return false;
			}
			
			return true;
	
		} catch (Exception e) {
			throw new InvalidJwtAutExcept("expirado ou token jwt invalido");
		}
	}
	
}
