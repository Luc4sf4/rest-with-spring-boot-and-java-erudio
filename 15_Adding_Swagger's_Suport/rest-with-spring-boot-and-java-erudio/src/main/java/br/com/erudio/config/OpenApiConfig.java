package br.com.erudio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("RESTful API with Java 18 and Spring Boot")
				.version("v1")
				.description("API used to learn JAVA")
				.termsOfService("https://www.linkedin.com/in/lucas-fernandes-dias-7860a328b/")
				.license(new License().name("Apache 2.0")
						.url("https://www.linkedin.com/in/lucas-fernandes-dias-7860a328b/"))
				);
		
		
	}
	

}
