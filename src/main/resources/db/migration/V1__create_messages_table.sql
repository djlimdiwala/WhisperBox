CREATE TABLE messages
(
    id BIGSERIAL PRIMARY KEY,

    sender VARCHAR(20) NOT NULL,

    receiver VARCHAR(20) NOT NULL,

    message TEXT NOT NULL,

    created_at TIMESTAMP NOT NULL,

    expires_at TIMESTAMP NOT NULL
);