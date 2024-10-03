package com.example.weatherdatastorage.validation;

import com.example.weatherdatastorage.repository.entity.CitiesWeather;
import com.example.weatherdatastorage.repository.impl.RepositoryMongoCitiesWeather;
import com.example.weatherdatastorage.repository.impl.StationRepository;
import dto.Request.CreateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Validation {
    private final RepositoryMongoCitiesWeather repositoryMongoCitiesWeather;
    private final StationRepository stationRepository;

    @Autowired
    public Validation(RepositoryMongoCitiesWeather repositoryMongoCitiesWeather, StationRepository stationRepository) {
        this.repositoryMongoCitiesWeather = repositoryMongoCitiesWeather;
        this.stationRepository = stationRepository;
    }

    public void isStationExist(CreateEntity createEntity) {
        String station = String.valueOf(createEntity.getStationNumber());
        String city = stationRepository.findByStationNumber(station).getCityName();
        if (!Objects.equals(createEntity.getCity(), city)) {
            throw new IllegalArgumentException("Город с именем " + createEntity.getCity() + " отсутсвтует в справочнике");
        }
        CitiesWeather t = repositoryMongoCitiesWeather.findByCity(city);
        double minTemp = t.getTemperatureRange().getMin();
        double maxTemp = t.getTemperatureRange().getMax();
        double temp = createEntity.getTemperature();
        if (temp < minTemp || temp > maxTemp) {
            throw new IllegalArgumentException("Температура " + temp + " невозможна!");
        }

    }

}
