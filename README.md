# WhisperBox

> A secure and lightweight Spring Boot backend for exchanging ephemeral (self-expiring) messages between users.

---

## 📖 Overview

WhisperBox is a RESTful backend application built using Java and Spring Boot.

The application allows predefined users to exchange messages through REST APIs. Messages can automatically expire after a configurable duration and support read-once behavior, making WhisperBox suitable for temporary and privacy-focused communication.

This project is being developed incrementally while following backend development best practices, including layered architecture, database migrations, validation, exception handling, scheduling, and clean API design.

---

## ✨ Features

Current features include:

- Send messages between predefined users
- Retrieve received messages
- View conversation history
- Read-once messaging
- Automatic message expiration
- Scheduled cleanup of expired messages
- Request validation
- Global exception handling
- Configuration using Spring Boot Configuration Properties
- Database versioning using Flyway
- PostgreSQL database hosted on Neon
- Layered architecture (Controller → Service → Repository)

---

## 🛠 Technology Stack

| Category | Technology |
|----------|------------|
| Language | Java 17 |
| Framework | Spring Boot 3 |
| Database | PostgreSQL |
| Cloud Database | Neon |
| Database Access | Spring Data JDBC |
| Database Migration | Flyway |
| Build Tool | Maven |
| Validation | Jakarta Validation |
| Scheduling | Spring Scheduler |
| Version Control | Git & GitHub |

---

## 📂 Project Structure

```text
WhisperBox
│
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
│   │
│   └── test
│
├── docs
│
├── pom.xml
│
└── README.md
```

---

## 📚 Documentation

Detailed documentation is available inside the `docs/` directory.

| Document | Description |
|----------|-------------|
| 01_Project_Overview.md | Project goals and objectives |
| 02_Architecture.md | System architecture and design |
| 03_API_Documentation.md | REST API reference |
| 04_Database.md | Database schema and migrations |
| 05_Development_Journey.md | Complete implementation journey |
| 06_Roadmap.md | Planned milestones |
| 07_Design_Decisions.md | Architectural decisions |

---

## 🚧 Current Status

The project currently supports:

- Sending messages
- Reading messages
- Conversation history
- Read-once functionality
- Message expiration
- Scheduled cleanup
- Validation
- Exception handling

More features are planned and documented in `docs/06_Roadmap.md`.

---

# 🚀 Getting Started

## Clone the Repository

```bash
git clone <repository-url>
```

## Build

```bash
mvn clean compile
```

## Run

```bash
mvn spring-boot:run
```

The application starts on:

```
http://localhost:8080
```

---

# 📖 API Examples

Send a message:

```bash
curl -X POST http://localhost:8080/messages/A \
-H "Content-Type: application/json" \
-d '{"message":"Hello"}'
```

Fetch inbox:

```bash
curl http://localhost:8080/messages/A
```

Conversation history:

```bash
curl http://localhost:8080/messages/conversation/A
```

---

# 🤝 Contributing

Contributions, suggestions, and improvements are welcome.

Please ensure that:

- Code follows existing project structure.
- New features include documentation updates.
- Database changes are introduced through Flyway migrations.
- All tests pass before creating a pull request.

---

# 📄 License

This project is intended for learning and demonstration purposes.
