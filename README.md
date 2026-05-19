# 👨‍💼 Employee Management REST API
 
A production-grade **Employee Management REST API** built with **Spring Boot**, following clean layered architecture, proper HTTP conventions, global exception handling, validation, API documentation and application monitoring.
 
> Built brick by brick — the way real companies expect it.
 
---
 
## 🔗 GitHub Repository
[https://github.com/Yasif17/EmployeeManagement.git](https://github.com/Yasif17/EmployeeManagement.git)
 
---
 
## 🛠️ Tech Stack
 
| Technology | Purpose |
|---|---|
| Java 17 | Core language |
| Spring Boot 3.x | Application framework |
| Spring Data JPA | Database interaction |
| PostgreSQL | Relational database |
| Hibernate | ORM |
| Lombok | Boilerplate reduction |
| Bean Validation | Input validation |
| SpringDoc OpenAPI | Swagger UI documentation |
| Spring Boot Actuator | Application monitoring |
| Maven | Build tool |
 
---
 
## 📁 Project Structure
 
```
src/
├── main/
│   ├── java/
│   │   └── com/Callofcoders/EmployeeManage/
│   │       ├── controllers/        # REST Controllers
│   │       ├── services/           # Business Logic
│   │       │   └── impl/           # Service Implementations
│   │       ├── repositories/       # JPA Repositories
│   │       ├── entities/           # JPA Entities
│   │       ├── dtos/               # Data Transfer Objects
│   │       ├── exceptions/         # Custom Exceptions
│   │       ├── advices/            # Global Exception Handler
│   │       └── configs/            # Configuration Classes
│   └── resources/
│       └── application.properties  # App Configuration
```
 
---
 
## ✅ Features
 
- Full **CRUD** operations for Employee data
- Clean **Layered Architecture** — Controller → Service → Repository
- **DTO Pattern** — entities never exposed directly to API
- **Manual Mapping** — `mapToDto()` and `mapToEntity()` helper methods
- **Global Exception Handling** — `@RestControllerAdvice` with structured error responses
- **Bean Validation** — `@Valid`, `@NotNull`, `@Email`, `@Pattern` on DTOs
- **Correct HTTP Status Codes** — 201 Created, 200 OK, 204 No Content
- **PUT vs PATCH** — full update vs partial update properly implemented
- **Spring Boot Actuator** — health monitoring endpoints
- **Swagger UI** — live API documentation and testing
---
 
## 📡 API Endpoints
 
### Employee Endpoints
 
| Method | Endpoint | Description | Status Code |
|---|---|---|---|
| GET | `/employees` | Get all employees | 200 OK |
| GET | `/employees/{id}` | Get employee by ID | 200 OK |
| POST | `/employees` | Create new employee | 201 Created |
| PUT | `/employees/{id}` | Full update employee | 200 OK |
| PATCH | `/employees/{id}` | Partial update employee | 200 OK |
| DELETE | `/employees/{id}` | Delete employee | 204 No Content |
 
---
 
## 📝 Request & Response Examples
 
### Create Employee — POST `/employees`
```json
// Request Body
{
    "name"   : "Yasif Khan",
    "email"  : "yasif@gmail.com",
    "salary" : 50000
}
 
// Response — 201 Created
{
    "id"     : 1,
    "name"   : "Yasif Khan",
    "email"  : "yasif@gmail.com",
    "salary" : 50000
}
```
 
### Partial Update — PATCH `/employees/1`
```json
// Request Body — only send fields to update
{
    "salary" : 60000
}
 
// Response — 200 OK
{
    "id"     : 1,
    "name"   : "Yasif Khan",
    "email"  : "yasif@gmail.com",
    "salary" : 60000
}
```
 
### Validation Error — POST `/employees` with missing fields
```json
// Response — 400 Bad Request
{
    "name"  : "Name cannot be null",
    "email" : "Email should be valid"
}
```
 
### Resource Not Found — GET `/employees/999`
```json
// Response — 404 Not Found
{
    "message"   : "Employee not found with id 999",
    "status"    : 404,
    "timestamp" : "2026-05-19T10:00:00"
}
```
 
---
 
## ⚙️ Setup & Installation
 
### Prerequisites
- Java 17+
- PostgreSQL
- Maven
### Step 1 — Clone the Repository
```bash
git clone https://github.com/Yasif17/EmployeeManagement.git
cd EmployeeManagement
```
 
### Step 2 — Configure Database
 
Create a PostgreSQL database:
```sql
CREATE DATABASE employee_management;
```
 
Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/employee_management
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
 
### Step 3 — Run the Application
```bash
mvn spring-boot:run
```
 
Application starts at: `http://localhost:8080`
 
---
 
## 📖 API Documentation — Swagger UI
 
After running the application, access Swagger UI:
 
```
http://localhost:8080/swagger-ui.html
```
 
---
 
## 📊 Actuator — Health Monitoring
 
```
http://localhost:8080/actuator           # all endpoints
http://localhost:8080/actuator/health    # app health
http://localhost:8080/actuator/metrics   # CPU, memory
http://localhost:8080/actuator/info      # app info
```
 
Add to `application.properties`:
```properties
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
```
 
---
 
## 🔒 Validation Rules
 
| Field | Rules |
|---|---|
| `name` | Not null, Not blank |
| `email` | Not null, Valid email format, Valid domain |
| `salary` | Not null |
 
---
 
## 🚨 Exception Handling
 
All exceptions are handled globally via `@RestControllerAdvice`:
 
| Exception | HTTP Status |
|---|---|
| `ResourceNotFoundException` | 404 Not Found |
| `MethodArgumentNotValidException` | 400 Bad Request |
| `RuntimeException` | 500 Internal Server Error |
 
---
 
## 🔄 PUT vs PATCH — Key Difference
 
| | PUT | PATCH |
|---|---|---|
| Sends | Full object | Only changed fields |
| Missing fields | Set to null | Remain unchanged |
| Validation | `@Valid` applied | No `@Valid` |
| Use case | Full replacement | Partial update |
 
---
 
## 🚀 What's Coming Next
 
- [ ] JWT Authentication
- [ ] Signup & Login
- [ ] Role Based Access Control (ADMIN / USER)
- [ ] Full Spring Security implementation
---
 
## 👨‍💻 Author
 
**Yasif Khan**
MCA Postgraduate | Java Spring Boot Developer
 
- GitHub: [@Yasif17](https://github.com/Yasif17)
---
 
## 📄 License
 
This project is open source and available under the [MIT License](LICENSE).
