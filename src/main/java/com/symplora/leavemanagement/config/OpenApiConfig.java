package com.symplora.leavemanagement.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info()
                .title("Leave Management API")
                .description("REST APIs for employee leave life-cycle")
                .version("v1")
                .contact(new Contact().name("Team Symplora").email("support@symplora.com")));
    }
}
