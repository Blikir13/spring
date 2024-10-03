package com.example.weatherdatastorage.repository.impl;

import com.example.weatherdatastorage.repository.entity.Station;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StationRepository extends MongoRepository<Station, String> {
    Station findByStationNumber(String stationNumber);
}
