package com.example.weatherdatastorage;

import com.example.weatherdatastorage.client.WeatherStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherDataStorageApplication implements CommandLineRunner {

    private final WeatherStorageClient weatherStorageClient;

    @Autowired
    public WeatherDataStorageApplication(WeatherStorageClient weatherStorageClient){
        this.weatherStorageClient = weatherStorageClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(WeatherDataStorageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        weatherStorageClient.sendRequest();
    }
}
