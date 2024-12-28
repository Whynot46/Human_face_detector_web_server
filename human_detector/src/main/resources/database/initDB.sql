-- Создание таблицы roles
CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,  -- Автоинкрементный идентификатор
    name VARCHAR(255) NOT NULL  -- Строка для имени роли
);

-- Создание таблицы users
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,  -- Автоинкрементный идентификатор
    username VARCHAR(255) NOT NULL,  -- Логин пользователя
    first_name VARCHAR(255) NOT NULL,  -- Имя пользователя
    middle_name VARCHAR(255),  -- Отчество пользователя
    last_name VARCHAR(255) NOT NULL,  -- Фамилия пользователя
    password_hash VARCHAR(255) NOT NULL,  -- пароль пользователя
    role_id INT REFERENCES roles(id)  -- Внешний ключ на таблицу roles
);
