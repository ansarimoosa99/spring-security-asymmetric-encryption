package com.ansari.app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Spring Security JWT Asymmetric Encryption",
                        email = "ansarimoosa99@gmail.com",
                        url = "https://moosaansari.netlify.app/"
                ),
                description = "OpenAPI documentation for Spring Security JWT Asymmetric Encryption Project",
                title = "OpenAPI Specification",
                version = "1.0",
                license = @License(
                        name = "License Name",
                        url = "https//github.com/ansarimoosa99/spring-security-jwt-asymmetric-encryption"
                ),
                termsOfService = "https://moosaansari.netlify.app/"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Local ENV"
                ),
                @Server(
                        description = "Prod ENV",
                        url = "https://spring-security-jwt-asymmetric-encryption.herokuapp.com/"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Bearer Authentication using JWT",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
        )
public class OpenApiConfig {
}
