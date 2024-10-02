package com.example.weatherdatastorage.service;

import com.example.weatherdatastorage.config.Config;
import dto.Request.CreateEntity;
import dto.Request.UpdateEntity;
import com.example.weatherdatastorage.mapper.WeatherStorageMapper;
import com.example.weatherdatastorage.repository.entity.Response.ResponseDto;
import com.example.weatherdatastorage.repository.entity.StationDataJsonEntity;
import com.example.weatherdatastorage.repository.impl.WeatherStorageJson;
import com.example.weatherdatastorage.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class WeatherStorageService {
    private final Config config;
    private final WeatherStorageJson stationDataJson;
    private final WeatherStorageMapper stationDataMapper;
    private final Validation validation;
    private static final Logger logger = Logger.getLogger(WeatherStorageService.class.getName());
    private static final String postfix = ".json";
    private static final String deleted = "Deleted";
    private static final String slash = "/";

    @Autowired
    public WeatherStorageService(WeatherStorageJson stationDataJson, WeatherStorageMapper stationDataMapper,
                                 Validation validation, Config config) {
        this.stationDataMapper = stationDataMapper;
        this.stationDataJson = stationDataJson;
        this.validation = validation;
        this.config = config;
    }

    public ResponseDto process(CreateEntity createEntity) {
        try {
            validation.isStationExist(createEntity);
            StationDataJsonEntity stationDataJsonEntity = stationDataMapper.toStationDataJsonEntity(createEntity);
            stationDataJson.write(stationDataJsonEntity, config.getResultJsonPath(), "");
            return new ResponseDto(stationDataJsonEntity.getId() + postfix, "");
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return new ResponseDto("", e.getMessage());
        }
    }


    public ResponseDto update(UpdateEntity updateEntity) {
        try {
            validation.isStationExist(updateEntity);
            StationDataJsonEntity stationDataJsonEntity = stationDataMapper.toStationDataJsonEntity(updateEntity);
            stationDataJson.write(stationDataJsonEntity, config.getResultJsonPath(), updateEntity.getPath());
            return new ResponseDto(stationDataJsonEntity.getId() + postfix, "");
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return new ResponseDto("", e.getMessage());
        }
    }

    public ResponseDto delete(String path) {
        File file = new File(config.getResultJsonPath() + slash + path);
        file.delete();
        return new ResponseDto(deleted + path, "");
    }

}
