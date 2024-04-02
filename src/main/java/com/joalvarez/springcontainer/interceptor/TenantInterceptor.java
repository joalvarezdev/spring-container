package com.joalvarez.springcontainer.interceptor;

import com.auth0.jwt.JWT;
import com.joalvarez.springcontainer.constants.ErrorCode;
import com.joalvarez.springcontainer.exception.generals.GenericException;
import com.joalvarez.springcontainer.utils.LocalStorage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.UUID;

@Component
public class TenantInterceptor implements HandlerInterceptor {

	private final LocalStorage storage;

	public TenantInterceptor(LocalStorage storage) {
		this.storage = storage;
	}


	@Override
	public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
		var token = SecurityContextHolder.getContext().getAuthentication().getDetails().toString();
		var claims = JWT.decode(token).getClaims();

		if (Objects.isNull(claims)) {
			throw new GenericException(HttpStatus.UNAUTHORIZED, ErrorCode.USER_BAD_CREDENTIALS);
		}

		this.storage.setUserDetails(UUID.fromString(claims.get("tenant").asString()));

		return true;
	}

	@Override
	public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, ModelAndView modelAndView) throws Exception {
		this.storage.clear();
	}

}
