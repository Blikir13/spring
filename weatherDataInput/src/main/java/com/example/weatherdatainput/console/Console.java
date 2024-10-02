package com.example.weatherdatainput.console;

import com.example.weatherdatainput.dto.StationDataDto;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.logging.Logger;

@Component
public class Console {
    private final Scanner scanner = new Scanner(System.in);
    private final Logger logger = Logger.getLogger(Console.class.getName());

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public <T extends Number> T getNumberInput(String prompt, Class<T> clazz) {
        System.out.print(prompt);
        while (true) {
            if (clazz == Integer.class && scanner.hasNextInt()) {
                int result = scanner.nextInt();
                scanner.nextLine();
                return clazz.cast(result);
            } else if (clazz == Double.class && scanner.hasNextDouble()) {
                double result = scanner.nextDouble();
                scanner.nextLine();
                return clazz.cast(result);
            } else {
                logger.warning("Please enter a valid number!");
                scanner.next();
            }
        }
    }

    public StationDataDto inputFromConsole() {
        StationDataDto stationDataDto = new StationDataDto();
        stationDataDto.setStationNumber(getNumberInput("Enter station number: ", Integer.class));
        stationDataDto.setCity(getStringInput("Enter city: "));
        stationDataDto.setTemperature(getNumberInput("Enter temperature: ", Double.class));
        stationDataDto.setPressure(getNumberInput("Enter pressure: ", Double.class));
        stationDataDto.setWindSpeed(getNumberInput("Enter wind speed: ", Double.class));
        stationDataDto.setWindDirection(getStringInput("Enter wind direction: "));
        return stationDataDto;
    }
}
