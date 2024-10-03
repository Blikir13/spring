package com.example.weatherdatastorage.repository.impl;

import com.example.weatherdatastorage.repository.entity.CitiesWeather;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositoryMongoCitiesWeather extends MongoRepository<CitiesWeather, String> {
    CitiesWeather findByCity(String city);
}
