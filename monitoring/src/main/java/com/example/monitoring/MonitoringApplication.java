package com.example.monitoring;

import com.example.monitoring.client.DataReceiverClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MonitoringApplication implements CommandLineRunner {

    private final DataReceiverClient dataReceiverClient;

    public MonitoringApplication(DataReceiverClient dataReceiverClient) {
        this.dataReceiverClient = dataReceiverClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(MonitoringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dataReceiverClient.sendRequest();
    }

}
