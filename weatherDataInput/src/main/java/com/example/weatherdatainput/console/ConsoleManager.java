package com.example.weatherdatainput.console;

import com.example.weatherdatainput.config.Config;
import com.example.weatherdatainput.dto.StationDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.weatherdatainput.repository.entity.WeatherInputCsvEntity;
import com.example.weatherdatainput.service.WeatherInputService;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Logger;

@Component
public class ConsoleManager {
    private final WeatherInputService dataReceiverService;
    private final Console console;
    private final Logger logger = Logger.getLogger(ConsoleManager.class.getName());
    private boolean flag;

    @Autowired
    public ConsoleManager(WeatherInputService dataReceiverService, Console console) {
        this.dataReceiverService = dataReceiverService;
        this.console = console;
    }

    private boolean isKnownCommand(String command) {
        for (Commands commands : Commands.values()) {
            if (commands.name().equalsIgnoreCase(command)) {
                return true;
            }
        }
        return false;
    }

    private String scan() {
        return new Scanner(System.in).nextLine().toUpperCase(Locale.ROOT);
    }

    private void defineCommand(String scannedCommand) {
        Commands command = Commands.valueOf(scannedCommand);
        switch (command) {
            case HELP:
                command.printHelp();
                break;
            case CREATE:
                createRecord();
                break;
            case READ:
                readRecords();
                break;
            case DELETE:
                deleteRecord();
                break;
            case UPDATE:
                updateRecord();
                break;
            case EXIT:
                flag = false;
                break;
            default:
                break;
        }
    }

    public void scanCommand() {
        flag = true;
        while (flag) {
            System.out.println("Please enter a command: ");
            String scannedCommand = this.scan();
            if (!isKnownCommand(scannedCommand)) {
                logger.info("Unknown command");
            } else {
                defineCommand(scannedCommand);
            }
        }
    }

    private void createRecord() {
        StationDataDto stationDataDto = console.inputFromConsole();
        dataReceiverService.createRequest(stationDataDto);
    }

    private void readRecords() {
        List<WeatherInputCsvEntity> readRecords = dataReceiverService.read();
        System.out.println("Station Info: ");
        for (WeatherInputCsvEntity record : readRecords) {
            System.out.println(record);
        }
    }

    private void deleteRecord() {
        int id = console.getNumberInput("Enter ID to delete: ", Integer.class);
        if (dataReceiverService.checkHasId(id)) {
            logger.info("Unknown ID: " + id);
            return;
        }
        dataReceiverService.deleteRecordRequest(id);
    }

    private void updateRecord() {
        int id = console.getNumberInput("Enter ID to update: ", Integer.class);
        if (dataReceiverService.checkHasId(id)) {
            logger.info("Unknown ID: " + id);
            return;
        }
        StationDataDto stationDataDto = console.inputFromConsole();
        dataReceiverService.updateRequest(stationDataDto, id);
    }
}
