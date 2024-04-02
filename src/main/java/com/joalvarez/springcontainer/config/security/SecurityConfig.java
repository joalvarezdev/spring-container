package com.joalvarez.springcontainer.config.security;

import com.joalvarez.springcontainer.config.security.constants.SecurityProperties;
import com.joalvarez.springcontainer.config.security.jwt.JwtValidationFilter;
import com.joalvarez.springcontainer.config.security.jwt.Jwts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final Jwts utils;
	private final SecurityProperties properties;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	public SecurityConfig(SecurityProperties properties, Jwts utils) {
		this.properties = properties;
		this.utils = utils;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(auth -> {

				auth.requestMatchers("/auth/login").permitAll();
				auth.requestMatchers(this.properties.getExcludedPaths()).permitAll();

				this.properties.getEndpoints().forEach(endpoint -> auth.requestMatchers(endpoint.getPath()).hasAnyRole(endpoint.getAuthorities().toArray(String[]::new)));

				auth.requestMatchers(this.properties.getExcludedPaths()).permitAll()
					.anyRequest().authenticated();
			})
			.addFilterBefore(new JwtValidationFilter(this.utils), BasicAuthenticationFilter.class)
			.csrf(AbstractHttpConfigurer::disable)
			.cors(Customizer.withDefaults())
			.formLogin(AbstractHttpConfigurer::disable)
			.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.build();
	}

	@Bean
	public WebMvcConfigurer corsConfiguration() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods(
						HttpMethod.GET.name(),
						HttpMethod.POST.name(),
						HttpMethod.PUT.name(),
						HttpMethod.DELETE.name(),
						HttpMethod.HEAD.name()
					)
					.allowedHeaders("*");
			}
		};
	}
}
