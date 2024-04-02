package com.joalvarez.springcontainer.config;

import com.joalvarez.springcontainer.interceptor.TenantInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class FilterConfig implements WebMvcConfigurer {

	private final TenantInterceptor tenantInterceptor;

	public FilterConfig (TenantInterceptor tenantInterceptor) {
		this.tenantInterceptor = tenantInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.tenantInterceptor)
			.addPathPatterns("/products/**");
	}
}