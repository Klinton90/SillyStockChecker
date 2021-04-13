package com.example.sillystockchecker.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;

@Slf4j
@Service
public class CostcoService {

    private final WebClient webClient;

    @Autowired
    public CostcoService(WebClient webClient) {
        this.webClient = webClient;
    }

    public boolean checkUrl(String pathToProduct) {
        log.info("Checking URL=" + pathToProduct);
        try {
            Object mono = webClient.get().uri(pathToProduct)
                    .acceptCharset(StandardCharsets.UTF_8)
//                    .ifNoneMatch("*")
//                    .ifModifiedSince(ZonedDateTime.now())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(Duration.ofMillis(5000));

            return true;
        } catch (Exception e) {
            log.info("Error on calling Costco server. Error is: " + e.getMessage());
            return false;
        }
    }

}
