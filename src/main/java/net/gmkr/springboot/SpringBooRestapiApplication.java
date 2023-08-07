package net.gmkr.springboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "spring boot rest api documentation", description = "spring boot rest api documentation", version = "v1.0", contact = @Contact(name = "Mallikarjuna", email = "mallireddy1999916@gmail.com", url = "google.com"), license = @License(name = "APache 2.0", url = "yahoo")), externalDocs = @ExternalDocumentation(description = "User management Documentation", url = "google"))
public class SpringBooRestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBooRestapiApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
