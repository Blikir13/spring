package com.example.weatherdatainput.mapper;

import dto.Request.CreateEntity;
import dto.Request.UpdateEntity;
import com.example.weatherdatainput.dto.StationDataDto;
import org.springframework.stereotype.Component;
import com.example.weatherdatainput.repository.entity.WeatherInputCsvEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class WeatherInputMapper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public WeatherInputCsvEntity toStationDataCsvEntity(StationDataDto stationDataDto) {
        WeatherInputCsvEntity stationDataCsvEntity = new WeatherInputCsvEntity();
        stationDataCsvEntity.setId(1);  // TODO: id должен устанавливаться динамически в зависимости от репозитория
        stationDataCsvEntity.setStationNumber(stationDataDto.getStationNumber());
        String now = LocalDateTime.now().format(formatter);
        stationDataCsvEntity.setTimestamp(now);
        stationDataCsvEntity.setFileName("");
        return stationDataCsvEntity;
    }

    public CreateEntity toStationDataEntity(StationDataDto stationDataDto) {
        CreateEntity stationDataEntity = new CreateEntity();
        stationDataEntity.setStationNumber(stationDataDto.getStationNumber());
        stationDataEntity.setCity(stationDataDto.getCity());
        stationDataEntity.setPressure(stationDataDto.getPressure());
        stationDataEntity.setTemperature(stationDataDto.getTemperature());
        stationDataEntity.setWindSpeed(stationDataDto.getWindSpeed());
        stationDataEntity.setWindDirection(stationDataDto.getWindDirection());
        return stationDataEntity;
    }

    public UpdateEntity toUpdateEntity(StationDataDto stationDataDto, String path) {
        UpdateEntity updateEntity = new UpdateEntity();
        updateEntity.setStationNumber(stationDataDto.getStationNumber());
        updateEntity.setCity(stationDataDto.getCity());
        updateEntity.setPressure(stationDataDto.getPressure());
        updateEntity.setTemperature(stationDataDto.getTemperature());
        updateEntity.setWindSpeed(stationDataDto.getWindSpeed());
        updateEntity.setWindDirection(stationDataDto.getWindDirection());
        updateEntity.setPath(path);
        return updateEntity;
    }
}
