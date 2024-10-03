package com.example.weatherdatastorage.repository.impl;

import com.example.weatherdatastorage.repository.entity.CitiesWeather;
import com.example.weatherdatastorage.repository.entity.StationDataJsonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositoryMongo extends MongoRepository<StationDataJsonEntity, String> {
}

