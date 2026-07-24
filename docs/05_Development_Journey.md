# Development Journey

This document records the implementation journey of WhisperBox.

Each milestone builds upon the previous one.

---

# Milestone 1

## Project Initialization

Completed:

- Spring Boot project creation
- Maven configuration
- Java 17 setup

---

# Milestone 2

## PostgreSQL Integration

Completed:

- Neon PostgreSQL setup
- Database connectivity
- application.properties configuration

---

# Milestone 3

## Flyway

Completed:

- Initial migration
- Schema versioning
- Automatic migration on startup

---

# Milestone 4

## Repository Layer

Completed:

- Spring Data JDBC
- CRUD Repository
- Custom query methods

---

# Milestone 5

## Service Layer

Completed:

- Send message
- Fetch inbox
- Fetch conversation
- Business logic separation

---

# Milestone 6

## Controller Layer

Completed:

- REST endpoints
- JSON APIs
- DTO responses

---

# Milestone 7

## Read Once Messages

Completed:

- Added is_read column
- Inbox returns unread messages
- Messages automatically marked as read

---

# Milestone 8

## Message Expiration

Completed:

- expires_at column
- Repository filtering
- Automatic expiration logic

---

# Milestone 9

## Scheduler

Completed:

- Scheduled cleanup task
- Automatic deletion of expired messages

---

# Milestone 10

## Validation

Completed:

- Request validation
- Custom validation messages
- Invalid user validation

---

# Milestone 11

## Exception Handling

Completed:

- Global exception handler
- Consistent JSON error responses

---

# Lessons Learned

Throughout development we learned:

- Layered architecture
- Spring Boot fundamentals
- Spring Data JDBC
- Flyway migrations
- Validation
- Scheduling
- Exception handling
- Repository query generation
- DTO design
- Service-oriented architecture

Future milestones are documented in Roadmap.md.
