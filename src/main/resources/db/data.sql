/**
  user
*/
INSERT INTO user (username, password) VALUES ('admin', 'admin');
INSERT INTO user (username, password) VALUES ('user', 'user');
/**
  role
*/
INSERT INTO role (name, description) VALUES ('管理员', '可以进行任何操作');
INSERT INTO role (name, description) VALUES ('普通用户', '可以查看信息...');
/**
  permission
*/
INSERT INTO permission (name, description, url, allowed_operations) VALUES ('创建用户', '管理员创建用户', '/user', 'c');
INSERT INTO permission (name, description, url, allowed_operations) VALUES ('查看用户信息', '查看自己用户信息', '/user', 'r');
/**
  user_role
*/
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
/**
 role_permission
 */
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 2);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 2);