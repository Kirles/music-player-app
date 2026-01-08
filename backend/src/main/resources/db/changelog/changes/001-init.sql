-- liquibase formatted sql

-- changeset kirles:001-create-users
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,

    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    account_locked BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- changeset kirles:002-create-artists
CREATE TABLE artists (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(255) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- changeset kirles:003-create-releases
CREATE TABLE releases (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    title VARCHAR(255) NOT NULL,
    release_type ENUM('SINGLE', 'EP', 'ALBUM') NOT NULL,
    total_tracks INT,
    release_date DATE NOT NULL,
    artist_id BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (artist_id) REFERENCES artists(id) ON DELETE CASCADE
);

-- changeset kirles:004-create-tracks
CREATE TABLE tracks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    title VARCHAR(255) NOT NULL,
    duration_sec INT NOT NULL,
    release_id BIGINT NOT NULL,

    file_path VARCHAR(512) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (release_id) REFERENCES releases(id) ON DELETE CASCADE
);

-- changeset kirles:005-create-playlists
CREATE TABLE playlists (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    title VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    total_tracks INT,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- changeset kirles:006-create-playlist_track
CREATE TABLE playlist_track (
    playlist_id BIGINT NOT NULL,
    track_id BIGINT NOT NULL,

    PRIMARY KEY (playlist_id, track_id),
    FOREIGN KEY (playlist_id) REFERENCES playlists(id) ON DELETE CASCADE,
    FOREIGN KEY (track_id) REFERENCES tracks(id) ON DELETE CASCADE
);
