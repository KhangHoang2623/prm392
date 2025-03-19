package com.example.prm392.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth", // Tên scheme
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@OpenAPIDefinition(
        security = @SecurityRequirement(name = "bearerAuth")
)
public class OpenApiConfig {

    @Bean
   public OpenAPI openAPI(){

        return new OpenAPI().info(new Info().title("PRM392").version("1.0"))
                .addServersItem(new Server().url("http://localhost:8080"))
        .addServersItem(new Server().url("http://14.225.254.90:8080/"));
    }


}
