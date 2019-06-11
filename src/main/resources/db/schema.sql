/**
  User
 */
CREATE TABLE user(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR2(30) NOT NULL ,
  password VARCHAR2(256) NOT NULL
);

CREATE TABLE role(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR2(30) NOT NULL,
  description VARCHAR2(30) NOT NULL
);

CREATE TABLE user_role(
  user_id BIGINT,
  role_id BIGINT,
  PRIMARY KEY (user_id, role_id)
);

CREATE TABLE permission(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR2(30) NOT NULL,
  description VARCHAR2(30) NOT NULL,
  url VARCHAR2(256) NOT NULL,
  allowed_operations VARCHAR2(256) NOT NULL
);

CREATE TABLE role_permission(
  role_id BIGINT,
  permission_id BIGINT,
  PRIMARY KEY (role_id, permission_id)
);