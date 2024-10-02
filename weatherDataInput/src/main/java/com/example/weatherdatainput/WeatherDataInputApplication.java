package com.example.weatherdatainput;

import com.example.weatherdatainput.console.ConsoleManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherDataInputApplication implements CommandLineRunner {

    private final ConsoleManager consoleManager;

    public WeatherDataInputApplication(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(WeatherDataInputApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            consoleManager.scanCommand();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions properly as needed
        }
    }

}
