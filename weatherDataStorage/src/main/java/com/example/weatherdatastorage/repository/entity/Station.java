package com.example.weatherdatastorage.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stations")
public class Station {
    @Id
    private String id; // Уникальный ID, создаваемый MongoDB
    private String stationNumber;
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }
// Конструкторы, геттеры и сеттеры
}
