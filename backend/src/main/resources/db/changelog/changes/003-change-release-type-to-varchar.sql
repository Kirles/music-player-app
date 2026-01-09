-- liquibase formatted sql

-- changeset kirles:008-change-release-type-to-varchar
ALTER TABLE releases
    MODIFY COLUMN release_type VARCHAR(10) NOT NULL;