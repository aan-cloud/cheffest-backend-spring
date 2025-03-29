# Cheffest Spring API Documentation

## Overview
Cheffest Spring API is a backend service for managing users, carts, and food items in an online food ordering system. Built using Spring Boot, this API ensures efficient and scalable management of user interactions.

## Tech Stack
- **Spring Boot** - Core framework for backend development
- **Spring Data JPA** - ORM for database operations
- **Spring Web** - Handles RESTful API endpoints
- **PostgreSQL** - Database for persistent storage
- **Jakarta Persistence (JPA)** - Entity mapping and relationships
- **Lombok** - Reduces boilerplate code
- **Postman** - API documentation and testing

## Features
- **Auth system**: Register, login, and profile management
- **Cart Management**: Add, remove, and update items in the cart
- **Food Management**: Browse and retrieve food items
- **Order Processing**: Handle checkout and order placement

## API Documentation
Full API documentation is available via Postman:
[Cheffest API Documentation](https://www.postman.com/joint-operations-pilot-8366866/cheffest-spring-api/request/ppmmu06/food?action=share&creator=36210259&ctx=documentation)

## Database Schema
### User Table
| Column   | Type    | Constraints |
|----------|--------|-------------|
| id       | UUID   | Primary Key |
| name     | String | Unique, Not Null |
| email    | String | Unique, Not Null |
| avatarUrl| String | Optional |

### Cart Table
| Column  | Type  | Constraints |
|---------|------|-------------|
| id      | UUID | Primary Key |
| user_id | UUID | Foreign Key (Users) |

### Cart Items Table
| Column  | Type  | Constraints |
|---------|------|-------------|
| id      | UUID | Primary Key |
| cart_id | UUID | Foreign Key (Carts) |
| food_id | UUID | Foreign Key (Foods) |
| quantity| Int  | Not Null |

## Setup and Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/cheffest-spring-api.git
   ```
2. Navigate to the project directory:
   ```sh
   cd cheffest-spring-api
   ```
3. Set up environment variables (database URL, credentials, etc.)
4. Build and run the project:
   ```sh
   ./mvnw spring-boot:run
   ```

## Contact & Support
For any issues or contributions, feel free to raise an issue or contribute via PR in the GitHub repository.

