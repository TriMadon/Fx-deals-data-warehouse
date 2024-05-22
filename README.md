# Fx-deals-data-warehouse

## Overview
This project is a simple data warehouse to analyze foreign exchange (FX) deals. It allows you to accept and persist deal details into a PostgreSQL database.

## Features
- **Deal Fields**: Each FX deal includes a unique ID, from currency ISO code, to currency ISO code, deal timestamp, and deal amount.
- **Validation**: The system validates the deal structure for missing fields and correct formats.
- **Deduplication**: Duplicate deals are not imported (if all attributes except ID match).
- **No Rollback**: All valid deals are saved without rollback.
- **Database**: Uses PostgreSQL for data persistence.
- **Deployment**: Easily deployable using Docker, Docker Compose, and Makefile.

## Setup Instructions

### Prerequisites
- **Docker**: Make sure Docker is installed on your system.

### Building and Running the Application

1. **Clone the Repository**
    ```sh
    git clone https://github.com/yourusername/fx-deals-data-warehouse.git
    cd fx-deals-data-warehouse
    ```

2. **Build and Run the Project**
    ```sh
    make
    ```

   To only build the project:
   ```
   make build
   ```

   To only run the application:
   ```
   make up
   ```

### Stopping the Application
To stop the running application:
```sh
make down
```

or

```sh
docker-compose down
```

## Usage

### Adding a New FX Deal

To add a new FX deal to the warehouse, send a POST request to ```http://localhost:8080/fxdeal/add``` with the a JSON payload like so:

```json
{
    "fromCurrencyCode": "USD",
    "toCurrencyCode": "EUR",
    "dealTimestamp": "2023-05-21T12:34:56Z",
    "dealAmount": 1000.0
}
```


### Retrieving All FX Deals

To retrieve all FX deals recorded in the data warehouse, send a GET request to ```http://localhost:8080/fxdeal/all``` (no need for an input in this case).

```
Note: You can do these HTTP interactions easily by using API interaction tools such as Postman.
```


## Project Structure

- **src/main/java**: Contains the source code.
- **src/test/java**: Contains the test code.
- **Dockerfile**: Dockerfile for building the application image.
- **docker-compose.yml**: Docker Compose configuration for setting up the PostgreSQL database and the application.
- **Makefile**: Makefile to streamline building and running the application.
- **schema.sql**: SQL file for setting up the database schema automatically upon firing the containers.


## Logging and Error Handling

- **Logging**: Implemented using SLF4J and Logback for structured logging.
- **Error Handling**: Proper exception handling to ensure meaningful error messages are returned to the client.


## Testing

- **Unit Tests**: Comprehensive unit tests using JUnit and Mockito.
- **Running Tests**: To run the tests, use the following command:
    ```sh
    mvn test
    ```

