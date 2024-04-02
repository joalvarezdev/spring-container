package com.joalvarez.springcontainer.service;

import com.joalvarez.springcontainer.config.security.jwt.Jwts;
import com.joalvarez.springcontainer.constants.ErrorCode;
import com.joalvarez.springcontainer.data.dto.UserDTO;
import com.joalvarez.springcontainer.data.dto.generals.TokenResponseDTO;
import com.joalvarez.springcontainer.data.dto.generals.UserLoginDTO;
import com.joalvarez.springcontainer.exception.generals.AuthException;
import com.joalvarez.springcontainer.exception.generals.GenericException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final Jwts utils;
	private final UserService userService;
	private final ImplUserDetailsService authUser;

	public AuthService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
					   Jwts utils, UserService userService, ImplUserDetailsService authUser) {
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.utils = utils;
		this.userService = userService;
		this.authUser = authUser;
	}

	public TokenResponseDTO login(UserLoginDTO dto) {
		var username = dto.username();
		var user = this.authUser.loadUserByUsername(username);
		var password = user.getPassword();

		if (!this.passwordEncoder.matches(dto.password(), password)) {
			throw new GenericException(HttpStatus.UNAUTHORIZED, ErrorCode.USER_BAD_CREDENTIALS);
		}

		var authenticationToken = new UsernamePasswordAuthenticationToken(username, dto.password());
		Authentication authResult;

		try {
			authResult = this.authenticationManager.authenticate(authenticationToken);
		} catch (Exception e) {
			throw new AuthException(ErrorCode.USER_NOT_AUTHENTICATED.message());
		}

		return new TokenResponseDTO(this.utils.createToken(authResult), this.utils.getExpiration(), this.utils.getPrefix());
	}

	public UserDTO register(UserDTO dto) {
		dto.setPassword(this.passwordEncoder.encode(dto.getPassword()));
		return this.userService.register(dto);
	}
}
