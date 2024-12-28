-- Заполнение таблицы roles
INSERT INTO roles (name) VALUES
('ADMIN'),
('USER');

-- Заполнение таблицы users
INSERT INTO users (username, first_name, middle_name, last_name, password_hash, role_id) VALUES
('admin', 'Реально', 'Крутой', 'Тип', '$2a$10$zkiBQmq5Ih3bZ4Y5UYWq8eiCsWoF.f29Tf0r3ymX9ZNl3UvTvWL.m', 1),  -- Администратор
('user',' Акакий', 'Акакиевич', 'Башмачник', '$2a$10$zkiBQmq5Ih3bZ4Y5UYWq8eiCsWoF.f29Tf0r3ymX9ZNl3UvTvWL.m', 2);  -- Учитель
