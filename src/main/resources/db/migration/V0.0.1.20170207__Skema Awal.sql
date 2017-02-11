/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since Feb 11, 2017
 * @Time 9:40:36 PM
 * @Encoding UTF-8
 * @Project Management-Backup-MongoDB
 * @Package Expression package is undefined on line 9, column 15 in Templates/Other/SQLTemplate.sql.
 *
 */

CREATE TABLE IF NOT EXISTS tb_log_clone (
    id VARCHAR(36) PRIMARY KEY,
    tanggal DATE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(45) NOT NULL,
    password VARCHAR(150) NOT NULL,
    enabled TINYINT NOT NULL DEFAULT 1 ,
    PRIMARY KEY (username)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS user_roles (
    user_role_id VARCHAR(36) NOT NULL,
    username VARCHAR(45) NOT NULL,
    role VARCHAR(45) NOT NULL,
    PRIMARY KEY (user_role_id),
    UNIQUE KEY uni_username_role (role,username),
    KEY fk_username_idx (username),
    CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS persistent_logins (
    series VARCHAR(64) PRIMARY KEY,
    username VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL
) ENGINE=InnoDB;