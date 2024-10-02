package com.example.weatherdatainput.client;

import com.example.weatherdatainput.config.Config;
import dto.Request.TransferableObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dto.Request.ResponseDto;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class WeatherStorageClient {
    private final Logger logger = Logger.getLogger(WeatherStorageClient.class.getName());
    private final String host;
    private final int processorPort;

    @Autowired
    public WeatherStorageClient(Config config) {
        this.host = config.getHost();
        this.processorPort = config.getProcessorPort();
    }

    public String sendRequest(TransferableObject transferableObject) {
        try (Socket socket = new Socket(host, processorPort);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {

            objectOutputStream.writeObject(transferableObject);
            objectOutputStream.flush();

            ResponseDto response = (ResponseDto) objectInputStream.readObject();
            if (Objects.equals(response.getId(), "")) {
                logger.warning("Error: " + response.getErrorMessage());
            } else {
                return response.getId();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        return "";
    }
}
