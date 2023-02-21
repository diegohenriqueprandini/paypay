package com.paypay.infra.config;

import com.paypay.utils.JsonUtils;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class ApiConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return JsonUtils.defaultJackson2ObjectMapperBuilder();
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info()
                .title("PAYPAY")
                .version("v1")
                .description("Api for Paypay")
                .termsOfService("Terms")
                .license(new License().name("APACHE").url("URL")));
    }
}
