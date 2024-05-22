# Define the project name and version
PROJECT_NAME := Fx-deals-data-warehouse
VERSION := 0.0.1-SNAPSHOT
JAR_FILE := $(PROJECT_NAME)-$(VERSION).jar

# Define the Docker image name
IMAGE_NAME := fx-deals-data-warehouse-app

# Maven commands
MVN_CLEAN := mvn clean
MVN_PACKAGE := mvn package -DskipTests

# Docker Compose commands
DOCKER_COMPOSE := docker-compose

.PHONY: all clean package build up down logs

# Default target
all: clean package build up

# Clean the Maven project
clean:
	$(MVN_CLEAN)

# Package the Maven project
package:
	$(MVN_PACKAGE)

# Build the Docker image
build:
	docker build -t $(IMAGE_NAME) .

# Bring up the Docker Compose services
up:
	$(DOCKER_COMPOSE) up -d

# Bring down the Docker Compose services
down:
	$(DOCKER_COMPOSE) down

# Tail the logs of the Docker Compose services
logs:
	$(DOCKER_COMPOSE) logs -f