INSERT INTO users (identification, first_name, last_name, email, vaccination_status, password, username)
VALUES ('0701234567', 'Sergio', 'H', 'x@y.z', 0, '$2a$12$lV2ae2YtprwdS2MXXfLmIehcyg0cu4dHfyA8EVdE7GM5RQoVCtUfW', 'HS01');

INSERT INTO users (identification, first_name, last_name, email, vaccination_status, password, username)
VALUES ('1023123454', 'Joshue', 'M', 'a@b.c', 1, '$2a$12$lV2ae2YtprwdS2MXXfLmIehcyg0cu4dHfyA8EVdE7GM5RQoVCtUfW', 'MJ02');

INSERT INTO users (identification, first_name, last_name, email, vaccination_status, password, username)
VALUES ('3423123454', 'Osiris', 'P', 'a@b.c', 1, '$2a$12$lV2ae2YtprwdS2MXXfLmIehcyg0cu4dHfyA8EVdE7GM5RQoVCtUfW', 'PO03');

INSERT INTO users (identification, first_name, last_name, email, vaccination_status, password, username)
VALUES ('2023123454', 'Willy', 'A', 'i@j.k', 0, '$2a$12$lV2ae2YtprwdS2MXXfLmIehcyg0cu4dHfyA8EVdE7GM5RQoVCtUfW', 'AW04');

INSERT INTO users (identification, first_name, last_name, email, vaccination_status, password, username)
VALUES ('5023123454', 'Pol', 'C', 't@j.k', 1, '$2a$12$lV2ae2YtprwdS2MXXfLmIehcyg0cu4dHfyA8EVdE7GM5RQoVCtUfW', 'CP05');

INSERT INTO users (identification, first_name, last_name, email, vaccination_status, password, username)
VALUES ('6023123454', 'Joni', 'C', 't@j.k', 1, '$2a$12$lV2ae2YtprwdS2MXXfLmIehcyg0cu4dHfyA8EVdE7GM5RQoVCtUfW', 'CJ06');

INSERT INTO users (identification, first_name, last_name, email, vaccination_status, password, username)
VALUES ('7823123454', 'Perro', 'C', 't@j.k', 1, '$2a$12$lV2ae2YtprwdS2MXXfLmIehcyg0cu4dHfyA8EVdE7GM5RQoVCtUfW', 'CP07');

-----------------------------------------------------------------------------------------

INSERT INTO user_vaccines (vaccine_type, vaccination_date, doses_number, user_id)
VALUES (0, '2021-12-09', 2, 2);

INSERT INTO user_vaccines (vaccine_type, vaccination_date, doses_number, user_id)
VALUES (1, '2021-09-09', 1, 3);

INSERT INTO user_vaccines (vaccine_type, vaccination_date, doses_number, user_id)
VALUES (1, '2021-05-09', 3, 5);

INSERT INTO user_vaccines (vaccine_type, vaccination_date, doses_number, user_id)
VALUES (2, '2021-12-19', 3, 2);

INSERT INTO user_vaccines (vaccine_type, vaccination_date, doses_number, user_id)
VALUES (3, '2022-01-19', 1, 6);

INSERT INTO user_vaccines (vaccine_type, vaccination_date, doses_number, user_id)
VALUES (3, '2022-03-19', 3, 7);

INSERT INTO user_vaccines (vaccine_type, vaccination_date, doses_number, user_id)
VALUES (0, '2021-09-09', 1, 3);

-----------------------------------------------------------------------------------------

INSERT INTO roles (role_name) VALUES ('EMPLOYEE');
INSERT INTO roles (role_name) VALUES ('ADMIN');

-----------------------------------------------------------------------------------------

INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (4, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (5, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (6, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (7, 1);