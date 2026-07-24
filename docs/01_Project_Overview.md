# Project Overview

## 1. Introduction

WhisperBox is a lightweight backend application that enables secure, temporary communication between predefined users.

Unlike traditional messaging applications that permanently store conversations, WhisperBox focuses on ephemeral messaging. Messages can automatically expire after a configured period and can optionally disappear after being read.

The application is intentionally designed as a backend-focused project to demonstrate modern Java and Spring Boot development practices.

---

## 2. Objectives

The primary objectives of WhisperBox are:

- Learn Spring Boot by building a real-world application.
- Understand layered backend architecture.
- Implement RESTful APIs.
- Work with PostgreSQL databases.
- Learn database migrations using Flyway.
- Use Spring Data JDBC for persistence.
- Implement request validation.
- Implement centralized exception handling.
- Build maintainable and testable code.
- Prepare the project for production deployment.

---

## 3. Target Audience

This project is intended for:

- Java developers learning Spring Boot.
- Backend developers.
- Students learning REST API development.
- Recruiters evaluating backend engineering skills.
- Contributors interested in improving the project.

---

## 4. Problem Statement

Many messaging applications permanently retain data. There are situations where users need to exchange information that should exist only temporarily.

Examples include:

- One-time passwords
- Temporary notes
- Confidential information
- Short-lived reminders
- Secure communication

WhisperBox addresses this requirement by supporting automatic expiration and read-once behavior.

---

## 5. Current Features

The current implementation supports:

- Sending messages
- Viewing received messages
- Viewing conversation history
- Read-once message retrieval
- Automatic expiration
- Scheduled cleanup of expired messages
- User validation
- Request validation
- Global exception handling
- PostgreSQL persistence
- Flyway database migrations

---

## 6. Technology Stack

| Component | Technology |
|-----------|------------|
| Language | Java 17 |
| Framework | Spring Boot |
| Build Tool | Maven |
| Database | PostgreSQL |
| Cloud Database | Neon |
| Data Access | Spring Data JDBC |
| Migration Tool | Flyway |
| Validation | Jakarta Validation |
| Scheduling | Spring Scheduler |

---

## 7. High-Level Workflow

1. A client sends an HTTP request.
2. The controller validates the request.
3. The service executes business logic.
4. The repository interacts with PostgreSQL.
5. The response is returned as JSON.
6. Scheduled jobs periodically remove expired messages.

---

## 8. Future Vision

The long-term vision for WhisperBox includes:

- Interactive API documentation using Swagger.
- Unit and integration testing.
- Docker containerization.
- CI/CD using GitHub Actions.
- Cloud deployment.
- Authentication using Spring Security and JWT.
- Encryption for stored messages.
- Frontend web application.
- Mobile application.
- Monitoring and observability.
