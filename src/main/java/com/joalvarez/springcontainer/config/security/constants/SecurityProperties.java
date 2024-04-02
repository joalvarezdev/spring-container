package com.joalvarez.springcontainer.config.security.constants;

import com.joalvarez.springcontainer.config.security.dto.EndPointSecurityDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "api.security")
public class SecurityProperties {

	private final String[] excludedPaths = {"/v3/api-docs/**", "/swagger-ui/**", "/actuator/health", "/error"};
	private List<EndPointSecurityDTO> endpoints;
	private List<String> origins;

	public List<EndPointSecurityDTO> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(List<EndPointSecurityDTO> endpoints) {
		this.endpoints = endpoints;
	}

	public String[] getExcludedPaths() {
		return excludedPaths;
	}

	public List<String> getOrigins() {
		return origins;
	}

	public void setOrigins(List<String> origins) {
		this.origins = origins;
	}
}
