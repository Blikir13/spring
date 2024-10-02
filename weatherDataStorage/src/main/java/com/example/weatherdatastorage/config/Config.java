package com.example.weatherdatastorage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class Config {

    @Value("${processor.port}")
    private int port;

    @Value("${processor.cities.json}")
    private String citiesJsonPath;

    @Value("${processor.temperature.json}")
    private String temperatureJsonPath;

    @Value("${processor.result.json}")
    private String resultJsonPath;
    private final String path = "application.properties";
    private final Logger logger = Logger.getLogger(Config.class.getName());



    public String getResultJsonPath() {
        return resultJsonPath;
    }

    public int getPort() {
        return port;
    }

    public String getCitiesJsonPath() {
        return citiesJsonPath;
    }

    public String getTemperatureJsonPath() {
        return temperatureJsonPath;
    }
}
