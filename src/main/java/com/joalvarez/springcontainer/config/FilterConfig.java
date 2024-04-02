package com.joalvarez.springcontainer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@Configuration
public class FilterConfig implements WebMvcConfigurer {

	//private final TenantInterceptor tenantInterceptor;

/*
	public FilterConfig (TenantInterceptor tenantInterceptor) {
		this.tenantInterceptor = tenantInterceptor;
	}
*/

/*
	private static final List<String> EXCLUDE_PATH = Stream.of(
		"/v3/api-docs/**",
		"/swagger-ui/**",
		"/swagger-ui.html",
		"/swagger-resources/**",
		"/webjars/**",
		"/auth/login"
	).toList();
*/

/*
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.tenantInterceptor)
			.excludePathPatterns(EXCLUDE_PATH);
	}
*/
}