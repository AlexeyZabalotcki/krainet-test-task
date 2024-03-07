package by.krainet.krainet.test.task.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Krainet CV Service",
                version = "1.0.0",
                description = "Service for managing the recruitment process"))
public class SwaggerConfig {

}
