package com.example.weatherdatainput.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.example.weatherdatainput.repository.entity.WeatherInputCsvEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvLoader {
    private static final String csvHeader = "id,station_number,timestamp,file_name";
    private static final String csvSeparator = ",";
    private final String filePath;


    public CsvLoader(@Value("${receiver.csv.filepath}") String filePath) {
        this.filePath = filePath;
    }

    private void createFileIfNotExists(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(csvHeader); //FIXME var? <3
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(csvHeader); //FIXME var? <3
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDataToFile(WeatherInputCsvEntity stationDataCsvEntity) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(stationDataCsvEntity.toCSV());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reWrite(List<WeatherInputCsvEntity> csvEntities) {
        File file = new File(filePath);
        createFile(file);
        for (WeatherInputCsvEntity csvEntity : csvEntities) {
            writeDataToFile(csvEntity);
        }
    }

    public void write(WeatherInputCsvEntity stationDataCsvEntity) {
        File file = new File(filePath);
        createFileIfNotExists(file);
        writeDataToFile(stationDataCsvEntity);
    }

    private List<WeatherInputCsvEntity> getLinesFromFile(BufferedReader bufferedReader) throws IOException {
        String header = bufferedReader.readLine(); //FIXME definition of read header?
        String line;
        List<WeatherInputCsvEntity> csvEntities = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) { //FIXME definition of read dataLine?
            String[] values = line.split(csvSeparator);

            WeatherInputCsvEntity record = new WeatherInputCsvEntity();
            record.setId(Integer.parseInt(values[0]));
            record.setStationNumber(Integer.parseInt(values[1]));
            record.setTimestamp(values[2]);
            if (values.length == 4) {
                record.setFileName(values[3]);
            } else {
                record.setFileName("");
            }

            csvEntities.add(record);
        }

        return csvEntities;
    }

    public List<WeatherInputCsvEntity> loadStationData() { //FIXME delete throws? <3
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        List<WeatherInputCsvEntity> csvEntities = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {//FIXME to method? <3
            csvEntities = getLinesFromFile(br);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvEntities;
    }
}
