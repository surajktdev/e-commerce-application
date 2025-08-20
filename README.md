# E-Commerce Application - Spring Boot Microservices
Note : I am Developing this as per My plan so many things is not available for now 
In future I am planning to create separate service for each module 

A scalable and robust e-commerce application built using Spring Boot with plans for full microservices architecture. Currently implements User and Product management in a unified service, with comprehensive API documentation using Swagger/OpenAPI.

## 🏗️ Current Architecture & Roadmap

### Currently Available Services
- **Core E-Commerce Service** - User authentication, authorization, and Product catalog management (Port: 8080)

### 🚀 Upcoming Microservices (Planned)
- **API Gateway Service** - Central entry point for all client requests
- **Order Service** - Order processing, order history, and order status management
- **Payment Service** - Payment processing and transaction management
- **Notification Service** - Email/SMS notifications and alerts
- **Shopping Cart Service** - Cart management and session handling
- **Inventory Service** - Stock management and availability tracking

## 🚀 Current Features

- **User Management**: Complete user authentication and authorization system
- **Product Catalog**: Full CRUD operations for products and categories
- **JWT Authentication**: Secure token-based authentication
- **API Documentation**: Interactive Swagger/OpenAPI documentation
- **Database Integration**: JPA/Hibernate with PostgreSQL/MySQL support
- **Input Validation**: Comprehensive request validation
- **Error Handling**: Global exception handling
- **Security**: Spring Security integration with role-based access control

## 🛠️ Tech Stack

### Backend Technologies
- **Java 17+** - Programming language
- **Spring Boot 3.x** - Application framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Data access layer
- **Spring Web** - REST API development
- **Spring Validation** - Input validation

### Database
- **PostgreSQL/MySQL** - Primary database

### Documentation & Testing
- **Swagger/OpenAPI 3** - API documentation
- **JUnit 5** - Unit testing
- **MockMvc** - Web layer testing
- **Testcontainers** - Integration testing

### Build & Development
- **Maven** - Build automation
- **Docker** - Containerization
- **Spring Boot DevTools** - Development productivity

## 📋 Prerequisites

- **Java 17** or higher
- **Maven 3.8+**
- **PostgreSQL/MySQL** (or use H2 for development)
- **Docker** (optional)

## 🔧 Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/surajktdev/e-commerce-application.git
cd e-commerce-application
```

### 2. Environment Configuration
Create `application.yml` or use environment variables:

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:postgresql://localhost:3306/e-commerce-db
    username: your_username
    password: your_password
    driver-class-name: org.postgresql.Driver
  
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect

  security:
    jwt:
      secret: your-256-bit-secret-key
      expiration: 86400000

# Swagger Configuration
springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
```

### 3. Database Setup

**Using PostgreSQL:**
```sql
CREATE DATABASE e-commerce-db;
CREATE USER ecommerce_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE ecommerce_db TO ecommerce_user;
```


### 4. Run the Application

**Using Maven:**
```bash
mvn spring-boot:run
```

**Using Java:**
```bash
mvn clean package
java -jar target/e-commerce-application-*.jar
```

**Using Docker:**
```bash
docker build -t ecommerce-app .
docker run -p 8080:8080 ecommerce-app
```

## 📁 Current Project Structure(🚫Not correct)

```
e-commerce-application/
├── src/
│   ├── main/
│   │   ├── java/com/surajktdev/ecommerce/
│   │   │   ├── EcommerceApplication.java
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── SwaggerConfig.java
│   │   │   │   └── JwtConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── UserController.java
│   │   │   │   └── ProductController.java
│   │   │   ├── service/
│   │   │   │   ├── UserService.java
│   │   │   │   └── ProductService.java
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   └── ProductRepository.java
│   │   │   ├── model/
│   │   │   │   ├── User.java
│   │   │   │   ├── Product.java
│   │   │   │   └── Category.java
│   │   │   ├── dto/
│   │   │   │   ├── UserDto.java
│   │   │   │   ├── ProductDto.java
│   │   │   │   └── AuthResponse.java
│   │   │   ├── exception/
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   └── util/
│   │   │       └── JwtUtil.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── data.sql
│   └── test/
│       └── java/com/surajktdev/ecommerce/
├── docs/
│   ├── API.md
│   ├── USER_SERVICE.md
│   └── PRODUCT_SERVICE.md
├── docker/
│   └── Dockerfile
├── postman/
│   └── E-Commerce_API.postman_collection.json
├── pom.xml
└── README.md
```

## 🔍 API Documentation

### Swagger UI Access
- **Main Application**: http://localhost:8080/swagger-ui.html
- **API Docs JSON**: http://localhost:8080/api-docs

### Current API Endpoints

#### Authentication APIs
```
POST /api/auth/register          # User registration
POST /api/auth/login            # User login
POST /api/auth/refresh-token    # Refresh JWT token
GET  /api/auth/profile          # Get user profile
PUT  /api/auth/profile          # Update user profile
```

#### User Management APIs
```
GET    /api/users               # Get all users (Admin only)
GET    /api/users/{id}          # Get user by ID (Admin only)
PUT    /api/users/{id}          # Update user (Admin only)
DELETE /api/users/{id}          # Delete user (Admin only)
GET    /api/users/me            # Get current user profile
```

#### Product Management APIs
```
GET    /api/products                    # Get all products
GET    /api/products/{id}               # Get product by ID
POST   /api/products                    # Create product (Admin only)
PUT    /api/products/{id}               # Update product (Admin only)
DELETE /api/products/{id}               # Delete product (Admin only)
GET    /api/products/search             # Search products
GET    /api/products/category/{category} # Get products by category
```

## 📚 Service Documentation

Each current and upcoming service will have its own detailed documentation:

### Current Services
- 📖 [User Service Documentation](docs/USER_SERVICE.md) - User authentication and management
- 📖 [Product Service Documentation](docs/PRODUCT_SERVICE.md) - Product catalog management

### Upcoming Services Documentation
- 📖 [Order Service Documentation](docs/ORDER_SERVICE.md) - *Coming Soon*
- 📖 [Payment Service Documentation](docs/PAYMENT_SERVICE.md) - *Coming Soon*
- 📖 [Cart Service Documentation](docs/CART_SERVICE.md) - *Coming Soon*
- 📖 [Inventory Service Documentation](docs/INVENTORY_SERVICE.md) - *Coming Soon*
- 📖 [Notification Service Documentation](docs/NOTIFICATION_SERVICE.md) - *Coming Soon*

## 🧪 Testing

### Unit Tests
```bash
# Run all tests
mvn test

# Run tests with coverage
mvn test jacoco:report
```

### Integration Tests
```bash
# Run integration tests
mvn verify -P integration-tests
```

### API Testing
Import the Postman collection from `/postman` directory for API testing.

## 📈 Monitoring & Health Checks

### Health Endpoints
- **Application Health**: http://localhost:8080/actuator/health
- **Application Info**: http://localhost:8080/actuator/info
- **Metrics**: http://localhost:8080/actuator/metrics

## 🔒 Security Implementation

### Current Security Features
- **JWT Authentication**: Stateless authentication with access and refresh tokens
- **Role-Based Access Control**: USER and ADMIN roles
- **Password Encryption**: BCrypt password hashing
- **Input Validation**: Bean validation for all endpoints
- **CORS Configuration**: Cross-origin resource sharing setup
- **Exception Handling**: Global exception handling with proper HTTP status codes

### Authentication Flow
1. User registers/logs in with credentials
2. Server validates and returns JWT access token
3. Client includes token in Authorization header for protected endpoints
4. Server validates token for each request

## 🚀 Future Development Plans

### Phase 1: Current ✅
- [x] User Management System
- [x] Product Catalog System
- [x] JWT Authentication
- [x] API Documentation with Swagger

### Phase 2: Microservices Migration 🔄
- [ ] Extract User Service into separate microservice
- [ ] Extract Product Service into separate microservice
- [ ] Implement API Gateway
- [ ] Service Discovery with Eureka

### Phase 3: Additional Services 📋
- [ ] Order Management Service
- [ ] Payment Processing Service
- [ ] Shopping Cart Service
- [ ] Inventory Management Service
- [ ] Notification Service

### Phase 4: Advanced Features 🎯
- [ ] Event-Driven Architecture with Kafka
- [ ] Distributed Caching with Redis
- [ ] Monitoring with Actuator & Micrometer
- [ ] Containerization with Docker
- [ ] Kubernetes Deployment

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Development Guidelines
- Follow Java naming conventions
- Write unit tests for new features
- Update API documentation
- Follow REST API best practices
- Use proper HTTP status codes

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**Suraj Kumar**
- GitHub: [@surajktdev](https://github.com/surajktdev)
- LinkedIn: [Your LinkedIn Profile]
- Email: [Your Email]

## 🙏 Acknowledgments

- Spring Boot community for excellent framework
- Swagger/OpenAPI for API documentation
- Open source contributors

## 🆘 Troubleshooting

### Common Issues

**Database Connection:**
```bash
# Check if database is running
sudo systemctl status postgresql

# Test database connection
psql -h localhost -U ecommerce_user -d ecommerce_db
```

**Port Already in Use:**
```bash
# Kill process using port 8080
lsof -ti:8080 | xargs kill -9
```

**JWT Token Issues:**
- Ensure JWT secret is at least 256 bits
- Check token expiration time
- Verify Authorization header format: `Bearer <token>`

---

**⭐ Star this repository if you found it helpful!**

**📖 For detailed API documentation, visit Swagger UI at http://localhost:8080/swagger-ui.html**

**🔮 Stay tuned for upcoming microservices implementation!**