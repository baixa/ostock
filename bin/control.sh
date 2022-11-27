#!/bin/sh
# Build JAR packages
mvn clean package dockerfile:build
# Create docker network
docker-compose -f docker/docker-compose.yml up -d