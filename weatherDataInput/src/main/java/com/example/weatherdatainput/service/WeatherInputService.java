package com.example.weatherdatainput.service;

import dto.Request.DeleteEntity;
import dto.Request.TransferableObject;
import com.example.weatherdatainput.dto.StationDataDto;
import com.example.weatherdatainput.mapper.WeatherInputMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.weatherdatainput.repository.entity.MonitoringDto;
import com.example.weatherdatainput.repository.entity.WeatherInputCsvEntity;
import com.example.weatherdatainput.repository.impl.RepositoryStationCsv;
import com.example.weatherdatainput.client.MonitoringClient;
import com.example.weatherdatainput.client.WeatherStorageClient;
import com.example.weatherdatainput.validation.Validation;

import java.util.List;
import java.util.logging.Logger;

@Service
public class WeatherInputService {
    private final static String CREATED = "created";
    private final static String MISTAKE = "mistake";
    private final static String SUCCESS = "success";
    private final WeatherInputMapper stationDataMapper;
    private final RepositoryStationCsv repositoryStationCsv;
    private final Validation validation = new Validation();
    private final WeatherStorageClient dataProcessorClient;
    private final MonitoringClient monitoringClient;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    public WeatherInputService(WeatherInputMapper stationDataMapper,
                               RepositoryStationCsv repositoryStationCsv,
                               WeatherStorageClient dataProcessorClient,
                               MonitoringClient monitoringClient) {
        this.stationDataMapper = stationDataMapper;
        this.repositoryStationCsv = repositoryStationCsv;
        this.dataProcessorClient = dataProcessorClient;
        this.monitoringClient = monitoringClient;
    }

    public boolean checkHasId(int id) {
        List<WeatherInputCsvEntity> weatherInputCsvEntities = repositoryStationCsv.read();
        return weatherInputCsvEntities.stream().noneMatch(entity -> entity.getId() == id);
    }

    public void createRequest(StationDataDto stationDataDto) {
        try {
            validation.firstValidation(stationDataDto);
            WeatherInputCsvEntity stationDataCsvEntity = stationDataMapper.toStationDataCsvEntity(stationDataDto);
            repositoryStationCsv.write(stationDataCsvEntity);

            monitoringClient.sendRequest(new MonitoringDto(stationDataCsvEntity.toString(), CREATED));
            TransferableObject transferableObject = stationDataMapper.toStationDataEntity(stationDataDto);
            String path = dataProcessorClient.sendRequest(transferableObject);

            if (!path.isEmpty()) {
                monitoringClient.sendRequest(new MonitoringDto(stationDataCsvEntity.toString(), MISTAKE));
                repositoryStationCsv.update(path);
            } else {
                monitoringClient.sendRequest(new MonitoringDto(stationDataCsvEntity.toString(), SUCCESS));
            }

        } catch (IllegalArgumentException e) {
            logger.severe(e.getMessage());
        }
    }

    public void deleteRecordRequest(int id) {
        String path = repositoryStationCsv.deleteRecord(id);
        if (!path.isEmpty()) {
            DeleteEntity deleteEntity = new DeleteEntity();
            deleteEntity.setPath(path);
            dataProcessorClient.sendRequest(deleteEntity);
        }
        logger.info("Deleted record with id: " + id);
    }

    public List<WeatherInputCsvEntity> read() {
        return repositoryStationCsv.read();
    }

    public void updateRequest(StationDataDto stationDataDto, int id) {
        try {
            validation.firstValidation(stationDataDto);
            String path = repositoryStationCsv.updateRecord(id, stationDataDto.getStationNumber());
            TransferableObject transferableObject = stationDataMapper.toUpdateEntity(stationDataDto, path);
            String newPath = dataProcessorClient.sendRequest(transferableObject);

            if (!newPath.isEmpty()) {
                repositoryStationCsv.updateWithId(newPath, id);
            }

            logger.info("Updated station data with id: " + id);
        } catch (IllegalArgumentException e) {
            logger.severe(e.getMessage());
        }
    }
}
