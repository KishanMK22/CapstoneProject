package com.stackroute.MovieApp.config;

/*
* Create a config class with RestTemplate bean
* */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * The AppConfig class is a configuration class in the application.
 * It is annotated with @Configuration to indicate that it is a Spring configuration class.
 * It contains a bean definition method for RestTemplate.
 */
@Configuration
public class AppConfig {
    /**
     * This method is a bean definition method for RestTemplate.
     * It is annotated with @Bean to indicate that it is a Spring bean.
     * The RestTemplate is used to make HTTP requests.
     * @return a new instance of RestTemplate.
     */
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}