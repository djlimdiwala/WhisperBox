# API Documentation

## Base URL

```
http://localhost:8080
```

---

# 1. Send Message

## Endpoint

```
POST /messages/{userKey}
```

Stores a new message for the configured receiver mapped to the supplied user key.

---

### Path Parameter

| Name | Description |
|------|-------------|
| userKey | Configured user (A, B, etc.) |

---

### Request Body

```json
{
    "message":"Hello"
}
```

---

### Success Response

HTTP 200

```json
{
    "success": true,
    "message": "Stored successfully"
}
```

---

### Validation Errors

HTTP 400

```json
{
    "success": false,
    "message": "Message cannot be blank"
}
```

---

### Invalid User

```json
{
    "success": false,
    "message": "Unknown user: C"
}
```

---

# 2. Get Inbox

## Endpoint

```
GET /messages/{receiver}
```

Returns all unread and non-expired messages for a receiver.

Messages are automatically marked as read after retrieval.

---

### Example

```
GET /messages/A
```

---

### Response

```json
[
  {
    "id":9,
    "sender":"B",
    "message":"Hello",
    "createdAt":"2026-07-24T12:30:00"
  }
]
```

---

# 3. Get Conversation

## Endpoint

```
GET /messages/conversation/{userKey}
```

Returns the entire conversation between the configured sender and receiver.

Unlike the inbox endpoint, conversation history does not delete or modify messages.

---

### Example

```
GET /messages/conversation/A
```

---

# Error Responses

## Validation Failure

HTTP 400

```json
{
    "success": false,
    "message":"Message cannot be blank"
}
```

---

## Invalid User

HTTP 400

```json
{
    "success": false,
    "message":"Unknown user: C"
}
```

---

# Notes

- JSON is used for all requests and responses.
- All timestamps are ISO-8601.
- Messages expire automatically.
- Expired messages are never returned.
- Read-once behaviour applies only to the inbox endpoint.
