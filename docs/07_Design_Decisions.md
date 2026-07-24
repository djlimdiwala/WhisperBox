# Design Decisions

This document explains why specific technologies and architectural approaches were selected.

---

# Why Spring Boot?

Spring Boot provides:

- Rapid development
- Embedded server
- Dependency injection
- Excellent ecosystem
- Production-ready features

---

# Why PostgreSQL?

PostgreSQL is:

- Reliable
- ACID compliant
- Open source
- Highly scalable
- Excellent Spring support

---

# Why Neon?

Neon provides:

- Managed PostgreSQL
- Free development tier
- Cloud-hosted database
- Automatic backups

---

# Why Spring Data JDBC instead of JPA?

For this project we intentionally selected Spring Data JDBC because:

- Simpler mental model
- Lightweight
- Less hidden behavior
- Better for learning SQL-oriented persistence

Future projects may use Spring Data JPA where ORM features are beneficial.

---

# Why Flyway?

Database schemas evolve over time.

Flyway provides:

- Version-controlled migrations
- Repeatable deployments
- Team collaboration
- Automatic startup migrations

---

# Why Layered Architecture?

The application separates responsibilities into:

Controller

↓

Service

↓

Repository

Benefits include:

- Easier maintenance
- Better testing
- Clear separation of concerns
- Cleaner code

---

# Why DTOs?

DTOs prevent exposing internal entities directly through the REST API.

Advantages:

- Stable API contracts
- Better security
- Easier evolution of entities

---

# Why Global Exception Handling?

Without a global handler every controller would duplicate error handling logic.

A centralized handler provides:

- Consistent responses
- Cleaner controllers
- Easier maintenance

---

# Why Validation?

Validation prevents invalid data from reaching the business layer.

Examples:

- Blank messages
- Invalid users

---

# Why Scheduled Cleanup?

Expired messages should not remain in the database forever.

A scheduler:

- Keeps storage clean
- Improves performance
- Removes expired data automatically

---

# Why Configuration Properties?

Application configuration should not be hardcoded.

ConfigurationProperties provides:

- Centralized configuration
- Strong typing
- Easier maintenance

---

# Future Architectural Decisions

As WhisperBox evolves, additional design decisions will be documented here, including:

- Authentication strategy
- Authorization model
- Deployment architecture
- Container orchestration
- Monitoring
- Distributed messaging
