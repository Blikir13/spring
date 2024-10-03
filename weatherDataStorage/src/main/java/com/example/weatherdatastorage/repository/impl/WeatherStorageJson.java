package com.example.weatherdatastorage.repository.impl;

import com.example.weatherdatastorage.repository.entity.StationDataJsonEntity;
import com.example.weatherdatastorage.util.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherStorageJson {
    private final JsonReader jsonReader;

    @Autowired
    public WeatherStorageJson(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    public void write(StationDataJsonEntity stationDataJsonEntity, String path, String updatePath) {
//        jsonReader.writeJson(stationDataJsonEntity, path, updatePath);
    }
}
