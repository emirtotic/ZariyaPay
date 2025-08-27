CREATE SCHEMA IF NOT EXISTS zariya_auth_users_db
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE zariya_auth_users_db;

CREATE TABLE IF NOT EXISTS `role`
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50)  NOT NULL,
    description VARCHAR(255) NULL,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT uk_role_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS `user`
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    password_hash VARCHAR(100) NOT NULL,
    first_name    VARCHAR(100) NULL,
    last_name     VARCHAR(100) NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    active        BOOLEAN      NOT NULL DEFAULT TRUE,
    role_id       BIGINT       NOT NULL,
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT uk_user_email UNIQUE (email),
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES `role` (id)
);

INSERT INTO `role` (name, description)
SELECT *
FROM (SELECT 'ROLE_USER' AS name, 'Standard user role' AS description) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `role` WHERE name = 'ROLE_USER');

INSERT INTO `role` (name, description)
SELECT *
FROM (SELECT 'ROLE_ADMIN' AS name, 'Administrator role' AS description) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `role` WHERE name = 'ROLE_ADMIN');