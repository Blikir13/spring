package com.example.monitoring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.monitoring.repository.entity.MonitoringDto;
import com.example.monitoring.repository.impl.LogFileRepository;

@Service
public class LoggingService {

    private final LogFileRepository logFileRepository;

    @Autowired
    public LoggingService(LogFileRepository logFileRepository) {
        this.logFileRepository = logFileRepository;
    }

    public void log(MonitoringDto monitoringEntity) {
        logFileRepository.writeLogEntry(monitoringEntity);
    }
}
