package com.Callofcoders.EmployeeManage.Employee.management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI employeeOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Management API")
                        .description("CRUD API for Employee Management")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Yasif")
                                .email("yasiffkhan@gmail.com")));
    }
}
