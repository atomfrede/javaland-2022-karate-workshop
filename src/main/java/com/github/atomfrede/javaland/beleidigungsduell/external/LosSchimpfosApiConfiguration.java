package com.github.atomfrede.javaland.beleidigungsduell.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class LosSchimpfosApiConfiguration {

    @Value("${javaland.losshimpfos.api}")
    private String apiEndpoint;

    @Bean
    public WebClient losSchimpfosWebClient() {
        return WebClient.create(apiEndpoint);
    }
}
