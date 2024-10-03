#!/bin/bash

# Запускаем docker-compose файл, указав путь относительно корня
docker-compose -f weatherDataInput/docker-compose.yml up -d
docker-compose -f weatherDataStorage/docker-compose.yml up -d