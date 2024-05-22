# Fx-deals-data-warehouse

## Overview
This project is a data warehouse for Bloomberg to analyze foreign exchange (FX) deals. It allows you to accept and persist deal details into a PostgreSQL database. The system ensures that no duplicate deals are imported, and all valid deals are saved in the database without allowing rollbacks.

## Features
- **Deal Fields**: Each FX deal includes a unique ID, from currency ISO code, to currency ISO code, deal timestamp, and deal amount.
- **Validation**: The system validates the deal structure for missing fields and correct formats.
- **Deduplication**: Duplicate deals are not imported.
- **No Rollback**: All valid deals are saved without rollback.
- **Database**: Uses PostgreSQL for data persistence.
- **Logging**: Proper logging for operations and errors.
- **Unit Testing**: Comprehensive unit tests with good coverage.
- **Deployment**: Easily deployable using Docker and Docker Compose.
- **Makefile**: Streamlined build and run process.

## Setup Instructions

### Prerequisites
- **Docker**: Make sure Docker is installed on your system.
- **Maven**: Ensure Maven is installed for building the project.

### Building and Running the Application

1. **Clone the Repository**
    ```sh
    git clone https://github.com/yourusername/fx-deals-data-warehouse.git
    cd fx-deals-data-warehouse
    ```

2. **Build the Project**
    ```sh
    make build
    ```

3. **Run the Application**
    ```sh
    make run
    ```

### Stopping the Application
To stop the running application:
```sh
docker-compose down
