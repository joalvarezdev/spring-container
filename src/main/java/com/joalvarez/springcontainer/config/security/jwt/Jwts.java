package com.joalvarez.springcontainer.config.security.jwt;

import com.joalvarez.springcontainer.constants.ErrorCode;
import com.joalvarez.springcontainer.data.model.User;
import com.joalvarez.springcontainer.exception.generals.GenericException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Jwts {

	@Value("${jwt.secret-key}")
	private String secretKey;
	@Value("${jwt.generator}")
	private String generator;
	@Value("${jwt.expiration-time}")
	private Long expiration;

	public String createToken(Authentication authentication) {
		var algorithm = Algorithm.HMAC256(this.secretKey);

		var user = (User) authentication.getPrincipal();
		var username = user.getUsername();
		var authorities = authentication.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(","));

		var currentDate = LocalDateTime.now();

		return JWT.create()
			.withIssuer(this.generator)
			.withSubject(username)
			.withClaim("id_user", user.getId())
			.withClaim("username", username)
			.withClaim("authorities", authorities)
			.withClaim("tenant", user.getUserId().toString())
			.withIssuedAt(Date.from(currentDate.atZone(ZoneId.systemDefault()).toInstant()))
			.withExpiresAt(new Date(currentDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() + this.expiration))
			.sign(algorithm);
	}

	public void validate(String token) {
		var algorithm = Algorithm.HMAC256(this.secretKey);

		try {
			var verifier = JWT.require(algorithm)
				.withIssuer(this.generator)
				.build();

			verifier.verify(token);
		} catch (JWTVerificationException e) {
			throw new GenericException(HttpStatus.UNAUTHORIZED, ErrorCode.USER_BAD_CREDENTIALS);
		}
	}

	public Map<String, Claim> getClaims(String token) {
		return this.decodeToken(token).getClaims();
	}

	private DecodedJWT decodeToken(String token) {
		return JWT.decode(token);
	}

	public long getExpiration() {
		return this.expiration;
	}

	public String getPrefix() {
		return "Bearer";
	}
}
