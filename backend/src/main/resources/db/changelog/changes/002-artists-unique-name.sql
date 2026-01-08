-- liquibase formatted sql

-- changeset kirles:007-unique-name
ALTER TABLE artists
    ADD UNIQUE (name);