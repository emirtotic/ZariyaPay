CREATE SCHEMA IF NOT EXISTS zariya_auth_users_db
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE zariya_auth_users_db;

CREATE TABLE IF NOT EXISTS `user`
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    external_id   CHAR(36)     NOT NULL,
    email         VARCHAR(254) NOT NULL,
    password_hash VARCHAR(100) NOT NULL,
    first_name    VARCHAR(100) NULL,
    last_name     VARCHAR(100) NULL,
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT uk_user_external UNIQUE (external_id),
    CONSTRAINT uk_user_email    UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS `role`
(
    id    BIGINT PRIMARY KEY AUTO_INCREMENT,
    code  VARCHAR(50)  NOT NULL,
    label VARCHAR(100) NULL,
    CONSTRAINT uk_role_code UNIQUE (code)
);

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_ur_user FOREIGN KEY (user_id) REFERENCES `user` (id) ON DELETE CASCADE,
    CONSTRAINT fk_ur_role FOREIGN KEY (role_id) REFERENCES `role` (id) ON DELETE CASCADE
);


INSERT INTO `role` (code, label)
SELECT * FROM (SELECT 'ROLE_USER' AS code, 'Standard user' AS label) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `role` WHERE code = 'ROLE_USER');

INSERT INTO `role` (code, label)
SELECT * FROM (SELECT 'ROLE_ADMIN' AS code, 'Administrator' AS label) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM `role` WHERE code = 'ROLE_ADMIN');
