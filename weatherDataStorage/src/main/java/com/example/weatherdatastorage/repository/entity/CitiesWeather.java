package com.example.weatherdatastorage.repository.entity;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "citiesWeather")
public class CitiesWeather {
    @Id
    private String id;
    private String city;
    private TemperatureRange temperatureRange;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public TemperatureRange getTemperatureRange() {
        return temperatureRange;
    }

    public void setTemperatureRange(TemperatureRange temperatureRange) {
        this.temperatureRange = temperatureRange;
    }
}

