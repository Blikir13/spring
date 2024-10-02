package com.example.weatherdatainput.repository;

import com.example.weatherdatainput.repository.entity.WeatherInputCsvEntity;

import java.io.IOException;

public interface Repository <E extends WeatherInputCsvEntity> {
    void write(E entity) throws IOException;
}
