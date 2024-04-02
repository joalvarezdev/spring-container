package com.joalvarez.springcontainer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@SecurityScheme(
	name = "Bearer Authentication",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	scheme = "bearer"
)
public class SwaggerConfig {

	@Value("${app.name}")
	private String name;
	@Value("${app.description}")
	private String description;
	@Value("${app.version}")
	private String version;

	@Bean
	public OpenAPI api() {
		License license = new License()
			.name("license")
			.url("licenseUrl");

		Contact contact = new Contact();

		Info info = new Info()
			.title(this.name)
			.description(this.description)
			.contact(contact)
			.license(license)
			.version(this.version);

		return new OpenAPI()
			.info(info);
	}
}