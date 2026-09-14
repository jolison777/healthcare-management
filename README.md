# MediCare360 - Enterprise Healthcare Appointment Management System

A Java Full Stack healthcare application built with **Java 17, Spring Boot, Spring Security, REST APIs, Angular, JPA/Hibernate, MySQL, Maven, JUnit and Mockito**.

## Features
- JWT authentication and role-based authorization
- Admin, Doctor and Patient roles
- Patient and doctor management
- Appointment booking, cancellation and status management
- Doctor availability
- Validation and global exception handling
- Layered Controller / Service / Repository architecture
- Angular dashboard and REST API integration
- MySQL database
- Unit testing with JUnit and Mockito

## Run
### Backend
1. Start MySQL or use `docker compose up mysql`.
2. From `backend`: `mvn spring-boot:run`
3. API: `http://localhost:8080/api`

### Frontend
From `frontend`: `npm install` then `npm start`
Open `http://localhost:4200`.

## Demo users
- Admin: `admin@medicare360.com` / `Admin@123`
- Doctor: `doctor@medicare360.com` / `Doctor@123`
- Patient: `patient@medicare360.com` / `Patient@123`

## Project structure
`backend/` Spring Boot REST API and MySQL persistence
`frontend/` Angular application
`docker-compose.yml` MySQL container
