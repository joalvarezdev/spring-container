package com.joalvarez.springcontainer.service;

import com.joalvarez.springcontainer.config.security.jwt.Jwts;
import com.joalvarez.springcontainer.constants.ErrorCode;
import com.joalvarez.springcontainer.data.dto.UserDTO;
import com.joalvarez.springcontainer.data.dto.generals.TokenResponseDTO;
import com.joalvarez.springcontainer.data.dto.generals.UserLoginDTO;
import com.joalvarez.springcontainer.exception.generals.AuthException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final Jwts utils;

	public AuthService(AuthenticationManager authenticationManager, Jwts utils) {
		this.authenticationManager = authenticationManager;
		this.utils = utils;
	}

	public TokenResponseDTO login(UserLoginDTO dto) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
		Authentication authResult;

		try {
			authResult = this.authenticationManager.authenticate(authenticationToken);
		} catch (Exception e) {
			throw new AuthException(ErrorCode.USER_NOT_AUTHENTICATED.message());
		}

		return new TokenResponseDTO(this.utils.createToken(authResult), this.utils.getExpiration(), this.utils.getPrefix());
	}

	public UserDTO register(UserDTO userDTO) {
		return new UserDTO();
	}
}
