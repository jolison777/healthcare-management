# MediCare360 — Enterprise Healthcare Appointment Management System

MediCare360 is a full-stack healthcare appointment management application built to demonstrate enterprise Java development practices.

## Technology Stack
- **Backend:** Java 17, Spring Boot 3.5, Spring MVC, Spring Data JPA, Hibernate, Spring Security
- **API Security:** JWT-style bearer authentication, BCrypt password hashing, role model (ADMIN/DOCTOR/PATIENT)
- **Frontend:** Angular 18, TypeScript, HTML/CSS
- **Database:** MySQL 8
- **Build/Test:** Maven, JUnit/Spring Boot Test
- **API Documentation:** Springdoc/OpenAPI dependency

## Features
- Patient self-registration and login
- JWT bearer authentication
- Role-aware user model
- Doctor and patient management APIs
- Appointment booking, listing, cancellation and status updates
- Layered Controller/Repository backend structure
- CORS configuration for Angular
- MySQL persistence with JPA/Hibernate
- Angular dashboard with Doctors, Patients and Appointments views
- Centralized development configuration with Docker Compose

## Project Structure
```text
healthcare-management/
├── backend/
│   ├── pom.xml
│   └── src/main/java/com/medicare360/
│       ├── auth/          # login, registration, users, token service
│       ├── appointment/   # appointment entity, repository, REST API
│       ├── doctor/        # doctor entity, repository, REST API
│       ├── patient/       # patient entity, repository, REST API
│       └── config/        # Spring Security and application configuration
├── frontend/
│   ├── package.json
│   └── src/app/           # Angular dashboard and authentication
└── docker-compose.yml     # MySQL development database
```

## Run Locally

### 1. Requirements
Install Java 17+, Maven 3.9+, Node.js 20+, npm, Git and Docker Desktop.

### 2. Start MySQL
From the repository root:
```bash
docker compose up -d mysql
```

The database is exposed on `localhost:3306` with database `medicare360`, username `root`, password `root` for local development.

### 3. Start the Spring Boot API
```bash
cd backend
mvn spring-boot:run
```
API base URL: `http://localhost:8080/api`

### 4. Start Angular
Open a second terminal:
```bash
cd frontend
npm install
npm start
```
Open `http://localhost:4200`.

### 5. Create your first account
Use **Create patient account** on the login page. Registration creates a PATIENT account. Then sign in with the same credentials.

## Useful API Endpoints
- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/doctors`
- `GET /api/patients`
- `GET /api/appointments`
- `POST /api/appointments`
- `PUT /api/appointments/{id}/status?value=CONFIRMED`
- `DELETE /api/appointments/{id}`

## Interview Talking Points
1. Request flow: Angular → REST Controller → Repository → JPA/Hibernate → MySQL.
2. Security: BCrypt password hashing plus bearer-token authentication and Spring Security filter chain.
3. Separation of concerns: authentication, domain modules and configuration are separated by package.
4. Persistence: Spring Data JPA removes boilerplate CRUD SQL while Hibernate maps entities to relational tables.
5. Validation and error handling can be extended centrally as the application grows.
6. The architecture is intentionally modular without introducing unnecessary microservice complexity for a portfolio project.
