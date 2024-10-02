package com.example.monitoring.client;

import com.example.monitoring.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.monitoring.repository.entity.MonitoringDto;
import com.example.monitoring.service.LoggingService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class DataReceiverClient {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private final int serverPort;

    private final LoggingService loggingService;

    @Autowired
    public DataReceiverClient(Config config, LoggingService loggingService) throws IOException {
        this.serverPort = config.getPort();
        this.loggingService = loggingService;
    }

    private void connect() throws IOException {
        serverSocket = new ServerSocket(serverPort);
    }

    private void acceptRequest() throws IOException {
        clientSocket = serverSocket.accept();
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
    }

    private void disconnect() throws IOException {
        objectOutputStream.close();
        objectInputStream.close();
        clientSocket.close();
    }

    public void sendRequest() throws IOException {
        try {
            connect();
            while (true) {
                acceptRequest();
                System.out.println("Клиент подключен.");
                MonitoringDto monitoringEntity = (MonitoringDto) objectInputStream.readObject();
                loggingService.log(monitoringEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }
}
