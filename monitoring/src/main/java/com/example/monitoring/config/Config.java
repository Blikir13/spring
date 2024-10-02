package com.example.monitoring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
    @Value("${monitoring.port}")
    private int port;

    @Value("${monitoring.logDirectory}")
    private String logDirectory;

    @Value("${monitoring.maxFileSize}")
    private long maxFileSize;

    @Value("${monitoring.rotationIntervalHours}")
    private int rotationIntervalHours;

    // Геттеры и сеттеры для полей
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getLogDirectory() {
        return logDirectory;
    }

    public void setLogDirectory(String logDirectory) {
        this.logDirectory = logDirectory;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public int getRotationIntervalHours() {
        return rotationIntervalHours;
    }

    public void setRotationIntervalHours(int rotationIntervalHours) {
        this.rotationIntervalHours = rotationIntervalHours;
    }
}
