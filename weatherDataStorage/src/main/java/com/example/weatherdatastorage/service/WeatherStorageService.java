package com.example.weatherdatastorage.service;

import com.example.weatherdatastorage.repository.impl.RepositoryMongo;
import dto.Request.CreateEntity;
import dto.Request.UpdateEntity;
import com.example.weatherdatastorage.mapper.WeatherStorageMapper;
import com.example.weatherdatastorage.repository.entity.StationDataJsonEntity;
import com.example.weatherdatastorage.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dto.Request.ResponseDto;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class WeatherStorageService {
    private final RepositoryMongo repositoryMongo;
    private final WeatherStorageMapper stationDataMapper;
    private final Validation validation;
    private static final Logger logger = Logger.getLogger(WeatherStorageService.class.getName());

    @Autowired
    public WeatherStorageService(WeatherStorageMapper stationDataMapper,
                                 Validation validation, RepositoryMongo repositoryMongo) {
        this.stationDataMapper = stationDataMapper;
        this.validation = validation;
        this.repositoryMongo = repositoryMongo;
    }

    public ResponseDto process(CreateEntity createEntity) {
        try {
            validation.isStationExist(createEntity);
            StationDataJsonEntity stationDataJsonEntity = stationDataMapper.toStationDataJsonEntity(createEntity);
            StationDataJsonEntity savedEntity = repositoryMongo.save(stationDataJsonEntity);
            return new ResponseDto(savedEntity.getId(), "");
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return new ResponseDto("", e.getMessage());
        }
    }


    public ResponseDto update(UpdateEntity updateEntity) {
        try {
            validation.isStationExist(updateEntity);
            StationDataJsonEntity stationDataJsonEntity = stationDataMapper.toStationDataJsonEntity(updateEntity);
            String id = updateEntity.getPath();
            if (!Objects.equals(id, "")) {
                stationDataJsonEntity.setId(id);
            }
            repositoryMongo.save(stationDataJsonEntity);
            return new ResponseDto(stationDataJsonEntity.getId(), "");
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return new ResponseDto("", e.getMessage());
        }
    }

    public ResponseDto delete(String id) {
        repositoryMongo.deleteById(id);
        return new ResponseDto("deleted" +  id, "");
    }

}
