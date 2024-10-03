package com.example.weatherdatainput.client;

import com.example.weatherdatainput.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dto.Request.MonitoringDto;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MonitoringClient {
    private final Logger logger = Logger.getLogger(MonitoringClient.class.getName());
    private final String host;
    private final int monitoringPort;

    @Autowired
    public MonitoringClient(Config config) {
        this.host = config.getHost();
        this.monitoringPort = config.getMonitoringPort();
    }

    public void sendRequest(MonitoringDto monitoringEntity) {
        try (Socket socket = new Socket(host, monitoringPort);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {

            objectOutputStream.writeObject(monitoringEntity);
            objectOutputStream.flush();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
