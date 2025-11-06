Spring Boot CRUD (Web, JPA, H2, Lombok)

Overview
- Spring Web for REST API
- Spring Data JPA for persistence
- H2 in-memory database
- Lombok for boilerplate reduction

Requirements
- Java 17+
- Maven 3.9+

Run
1) Start the app:
   mvn spring-boot:run

2) API endpoints:
- GET    /api/users
- GET    /api/users/{id}
- POST   /api/users
- PUT    /api/users/{id}
- DELETE /api/users/{id}

3) H2 console:
- http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testdb
- User: sa (no password)

