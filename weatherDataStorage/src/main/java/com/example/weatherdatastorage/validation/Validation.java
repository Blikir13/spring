package com.example.weatherdatastorage.validation;

import com.example.weatherdatastorage.config.Config;
import dto.Request.CreateEntity;
import com.example.weatherdatastorage.util.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Validation {
    private final JsonReader jsonReader;

    @Autowired
    public Validation(Config config) {
        this.jsonReader = new JsonReader(config.getTemperatureJsonPath(), config.getCitiesJsonPath());
    }

    public void isStationExist(CreateEntity createEntity) {
        String city = jsonReader.getCityByStation(createEntity.getStationNumber());
        if (!Objects.equals(createEntity.getCity(), city)) {
            throw new IllegalArgumentException("Город с именем " + createEntity.getCity() + " отсутсвтует в справочнике");
        }
        double minTemp = jsonReader.getMinTemperature(city);
        double maxTemp = jsonReader.getMaxTemperature(city);
        double temp = createEntity.getTemperature();
        if (temp < minTemp || temp > maxTemp) {
            throw new IllegalArgumentException("Температура " + temp + " невозможна!");
        }

    }

}
