# WhisperBox 💬

A lightweight anonymous two-user chat application built with **Spring Boot**, **PostgreSQL**, **WebSockets**, and **Docker**.

WhisperBox allows two users to communicate privately using secret URLs without creating accounts or logging in.

---

## Features

- 💬 Anonymous two-user chat
- ⚡ Real-time messaging using WebSockets (STOMP)
- 💾 PostgreSQL persistence
- 🔄 Conversation history
- 📩 Unread message tracking
- ✅ Input validation
- 📄 Swagger / OpenAPI documentation
- 🐳 Docker & Docker Compose support
- 🛠 Flyway database migrations
- 📱 Responsive mobile-friendly UI
- ❤️ Spring Boot Actuator health checks
- 📝 Production logging

---

## Screenshots

Add screenshots here after deployment.

### Chat Screen

![Chat](docs/screenshots/chat.png)

### Swagger

![Swagger](docs/screenshots/swagger.png)

---

## Technology Stack

| Technology | Version |
|------------|---------|
| Java | 17 |
| Spring Boot | 3.5 |
| PostgreSQL | Latest |
| Flyway | Latest |
| WebSocket (STOMP) | Spring |
| Docker | Latest |
| Maven | 3.9 |

---

## Architecture

```
Browser
       │
       ▼
Spring Boot REST API
       │
       ├────────────► PostgreSQL
       │
       ▼
Spring WebSocket
       │
       ▼
Real-time Updates
```

---

## Local Development

```bash
git clone <repository>

cd WhisperBox

mvn spring-boot:run
```

---

## Docker

Build

```bash
docker build -t whisperbox .
```

Run

```bash
docker compose up -d
```

---

## API Documentation

Swagger

```
http://localhost:8080/swagger-ui/index.html
```

Health

```
http://localhost:8080/actuator/health
```

---

## Deployment

The application is containerized using Docker and can be deployed on:

- Render
- Railway
- Fly.io
- Azure
- AWS
- Google Cloud

---

## Project Structure

```
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── config
 ├── exception
 └── resources
```

---

## Future Roadmap

- End-to-end encryption
- Typing indicator
- Read receipts
- Image sharing
- Voice messages
- Multiple conversations
- Self-destructing messages

---

## License

MIT License
