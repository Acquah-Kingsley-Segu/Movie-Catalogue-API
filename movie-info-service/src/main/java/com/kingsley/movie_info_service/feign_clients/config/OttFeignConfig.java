package com.kingsley.movie_info_service.feign_clients.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OttFeignConfig {
    @Value("${OTT_MOVIES_HOST}")
    String host;
    @Value("${OTT_MOVIES_KEY}")
    String key;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-Rapidapi-Host", host);
            requestTemplate.header("X-Rapidapi-Key", key);
        };
    }
}
