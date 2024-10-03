package com.example.weatherdatainput.repository.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.example.weatherdatainput.repository.entity.WeatherInputCsvEntity;
//import com.example.weatherdatainput.util.CsvLoader;

import java.util.List;
/*
@Repository
public class RepositoryStationCsv {
    private final CsvLoader csvLoader;

    // Внедрение пути через application.properties
    public RepositoryStationCsv(@Value("${receiver.csv.filepath}") String filePath) {
        this.csvLoader = new CsvLoader(filePath);
    }

    public void write(WeatherInputCsvEntity stationDataCsvEntity) {
        List<WeatherInputCsvEntity> csvEntities = csvLoader.loadStationData();
        if (csvEntities.isEmpty()) {
            stationDataCsvEntity.setId(1);
        } else {
            int newId = csvEntities.get(csvEntities.size() - 1).getId();
            stationDataCsvEntity.setId(++newId);
        }
        csvLoader.write(stationDataCsvEntity);
    }

    public void updateWithId(String path, int id) {
        List<WeatherInputCsvEntity> csvEntities = csvLoader.loadStationData();
        for (WeatherInputCsvEntity stationDataCsvEntity : csvEntities) {
            if (stationDataCsvEntity.getId() == id) {
                stationDataCsvEntity.setFileName(path);
            }
        }
        csvLoader.reWrite(csvEntities);
    }

    public List<WeatherInputCsvEntity> read() {
        return csvLoader.loadStationData();
    }

    public void update(int path) {
        List<WeatherInputCsvEntity> csvEntities = csvLoader.loadStationData();
        WeatherInputCsvEntity last = csvEntities.get(csvEntities.size() - 1);
        last.setFileName(path);
        csvLoader.reWrite(csvEntities);
    }

    public String deleteRecord(int id) {
        List<WeatherInputCsvEntity> csvEntities = csvLoader.loadStationData();
        int path = 0;
        for (int i = csvEntities.size() - 1; i >= 0; i--) {
            WeatherInputCsvEntity stationDataCsvEntity = csvEntities.get(i);
            if (stationDataCsvEntity.getId() == id) {
                path = stationDataCsvEntity.getFileName() != 0 ? stationDataCsvEntity.getFileName() : path;
                csvEntities.remove(i);
            }
        }
        csvLoader.reWrite(csvEntities);
        return path;
    }

    public String updateRecord(int id, int stationNumber) {
        List<WeatherInputCsvEntity> csvEntities = csvLoader.loadStationData();
        String path = "";
        for (int i = csvEntities.size() - 1; i >= 0; i--) {
            WeatherInputCsvEntity stationDataCsvEntity = csvEntities.get(i);
            if (stationDataCsvEntity.getId() == id) {
                path = stationDataCsvEntity.getFileName() != null ? stationDataCsvEntity.getFileName() : path;
                // TODO: возмонжо стоит обновлять номер станции после 2 валидации?
                stationDataCsvEntity.setStationNumber(stationNumber);
            }
        }
        csvLoader.reWrite(csvEntities);
        return path;
    }
}
*/