package com.example.weatherdatainput.repository.entity;
import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "station")
public class WeatherInputCsvEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int stationNumber;
    @Column(nullable = false)
    private String timestamp;
    @Column()
    private String fileName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String toCSV() {
        return id + "," + stationNumber + "," + timestamp + "," + fileName;
    }

    public String toString() {
        return id + "," + stationNumber + "," + timestamp + "," + fileName;
    }
}
