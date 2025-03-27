# Cart API Documentation

## Introduction
This API is a shopping cart system built with **Spring Boot**, allowing users to add, retrieve, and delete items from their cart.

## Tech Stack
- **Java 17**
- **Spring Boot** (Spring Web, Spring Data JPA)
- **PostgreSQL** (as the database)
- **Hibernate** (ORM for database interactions)
- **Maven** (for dependency management)
- **Lombok** (for reducing boilerplate code)
- **Docker** (optional, for containerization)

## API Endpoints

### 1. Add Food to Cart
**Endpoint:** `POST /cart/add`

**Request Body:**
```json
{
  "foodId": "uuid",
  "userId": "uuid",
  "sum": 2
}
```

**Response:**
```json
{
  "message": "Success add product to cart",
  "productName": "Pizza",
  "quantity": 2
}
```

---

### 2. Get User Cart
**Endpoint:** `GET /cart/user/{userId}`

**Response:**
```json
{
  "totalPrice": 29.99,
  "cart": {
    "id": "uuid",
    "userId": "uuid"
  },
  "items": [
    {
      "id": "uuid",
      "quantity": 2,
      "food": {
        "name": "Pizza",
        "price": 14.99
      }
    }
  ]
}
```

---

### 3. Delete Cart Item
**Endpoint:** `DELETE /cart/item/{cartItemId}/{slug}`

**Response:**
```json
{
  "message": "Success delete product",
  "productName": "Pizza"
}
```

## Installation & Setup

### 1. Clone the Repository
```sh
git clone https://github.com/your-repo/cart-api.git
cd cart-api
```

### 2. Configure the Database
Update `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cart_db
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 3. Run the Application
```sh
mvn spring-boot:run
```

## Deployment
You can deploy the application for free on the following platforms:
- **Railway.app**

## Contact
For any issues, contact **fmuh8403@gmail.com**.
