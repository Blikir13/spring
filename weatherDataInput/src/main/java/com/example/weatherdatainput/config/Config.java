package com.example.weatherdatainput.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {

    @Value("${receiver.host}")
    private String host;

    @Value("${receiver.processor.port}")
    private int processorPort;

    @Value("${receiver.monitoring.port}")
    private int monitoringPort;

    @Value("${receiver.csv.filepath}")
    private String path;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getProcessorPort() {
        return processorPort;
    }

    public void setProcessorPort(int processorPort) {
        this.processorPort = processorPort;
    }

    public int getMonitoringPort() {
        return monitoringPort;
    }

    public void setMonitoringPort(int monitoringPort) {
        this.monitoringPort = monitoringPort;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
