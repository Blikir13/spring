package com.example.weatherdatastorage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${processor.port}")
    private int port;

    public int getPort() {
        return port;
    }

}
