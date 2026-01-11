-- liquibase formatted sql

-- changeset kirles:009-create-refresh-token-table
CREATE TABLE refresh_token (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    token VARCHAR(255) NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    expiry_date TIMESTAMP NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);