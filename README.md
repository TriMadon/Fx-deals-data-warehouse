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
- **Logging**: Implemented using SLF4J and Logback for structured logging.

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

   To only build the project: ```make build```

   To only run the application: ```make up```

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

## Testing

- **Unit Tests**: Written using JUnit and Mockito.
- **Running Tests**: To run the tests, use the following command:
    ```sh
    mvn test
    ```


## Project Structure

### Main Classes

- **FxDeal**: This class represents the FX deal entity with fields like deal ID, from currency code, to currency code, deal timestamp, and deal amount.
- **FxDealController**: Handles the HTTP requests. It includes methods for adding a new FX deal and retrieving all FX deals.
- **FxDealService**: This service class contains the business logic for processing FX deals, including saving new deals and retrieving all deals from the database.
- **FxDealValidator**: Responsible for validating the FX deal data. It checks for missing fields and correct formats.
- **FxDealRepository**: A JpaRepository to provide database access methods for FX deals.

### Test Classes
- **FxDealServiceTest**: Includes unit tests for the FxDealService class methods.
- **FxDealControllerTest**: Includes unit tests for the FxDealController class methods.
