package com.joalvarez.springcontainer.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

public class JwtValidationFilter extends OncePerRequestFilter {

	private final Jwts utils;

	public JwtValidationFilter(Jwts utils) {
		this.utils = utils;
	}

	@Override
	protected void doFilterInternal(
		@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
		throws IOException, ServletException {

		var token = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (Objects.nonNull(token)) {
			token = token.substring(7);

			this.utils.validate(token);

			var claims = this.utils.getClaims(token);

			var username = claims.get("username");
			var stringAuthorities = claims.get("authorities").asString();

			var authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorities);

			var authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
			authenticationToken.setDetails(token);

			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		chain.doFilter(request, response);
	}
}
