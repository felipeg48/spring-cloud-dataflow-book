package com.apress.cloud.stream.movie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.SpelFunctionFactoryBean;
import org.springframework.integration.json.JsonPathUtils;

@Configuration
public class MovieConfiguration {
    @Bean
    public SpelFunctionFactoryBean jsonPath() {
        return new SpelFunctionFactoryBean(JsonPathUtils.class, "evaluate");
    }
}
