package com.example.weatherdatainput.repository.impl;

import com.example.weatherdatainput.repository.entity.WeatherInputCsvEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPg extends JpaRepository<WeatherInputCsvEntity, Integer> {
}
