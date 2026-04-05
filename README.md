# Movie Ticket Admin Service

## Overview
This project is a **Movie Ticket Admin Service** built using **Java** and **Spring Boot**. It provides APIs for managing movie ticket transactions, including ticket booking, holding, unholding, and retrieving transaction history.

## Features
- **User Authentication**: Validate user tokens for secure access.
- **Transaction History**: Retrieve a user's transaction history.
- **Ticket Management**:
    - Get available tickets for a specific movie time.
    - Hold tickets for a user.
    - Book tickets and update user balance/points.
    - Unhold tickets and release them back to availability.

## Technologies Used
- **Programming Language**: Java
- **Framework**: Spring Boot
- **Build Tool**: Maven
- **Database**: Relational database (e.g., MySQL/PostgreSQL)
- **IDE**: IntelliJ IDEA

## Project Structure
- `src/main/java/com/binhlc/movieticketadmin/service/`: Contains service classes for handling business logic.
- `src/main/java/com/binhlc/movieticketadmin/repository/`: Repository interfaces for database operations.
- `src/main/java/com/binhlc/movieticketadmin/entity/`: Entity classes representing database tables.
- `src/main/java/com/binhlc/movieticketadmin/controller/`: REST controllers for handling API requests.

## Key Endpoints
1. **Transaction History**
    - `GET /transactions/history`: Retrieve transaction history for a user.
2. **Ticket Management**
    - `GET /tickets`: Get available tickets for a movie time.
    - `POST /tickets/hold`: Hold tickets for a user.
    - `POST /tickets/book`: Book tickets and finalize the transaction.
    - `POST /tickets/unhold`: Unhold tickets and release them.

## How to Run
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```bash
   cd movieticketadmin
   ```
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```
5. Access the APIs at `http://localhost:8080`.

## Prerequisites
- **Java 17** or higher
- **Maven 3.8+**
- A relational database (configured in `application.properties`)

## Configuration
Update the database connection details in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/movieticketdb
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update
```

