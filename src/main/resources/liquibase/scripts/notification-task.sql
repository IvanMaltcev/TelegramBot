-- liquibase formatted sql

-- changeset Imaltcev:1
CREATE TABLE notificationTask (
Id SERIAL PRIMARY KEY,
chat_id INTEGER,
notification_text text,
date_and_time_notifications TIMESTAMP
);

-- changeset Imaltcev:2
ALTER TABLE notificationTask
RENAME COLUMN date_and_time_notifications TO date_time_notifications;
