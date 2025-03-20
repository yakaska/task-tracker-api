-- Создание таблицы пользователей
CREATE TABLE users
(
    id    SERIAL PRIMARY KEY,
    email      VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    role       VARCHAR(50)         NOT NULL CHECK (role IN ('ADMIN', 'USER')),
    created_at TIMESTAMP DEFAULT NOW()
);

-- Создание таблицы задач
CREATE TABLE tasks
(
    id     SERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    status      VARCHAR(50)  NOT NULL CHECK (status IN ('PENDING', 'IN_PROGRESS', 'COMPLETED')),
    priority    VARCHAR(50)  NOT NULL CHECK (priority IN ('HIGH', 'MEDIUM', 'LOW')),
    author_id   INT          NOT NULL,
    assignee_id INT,
    created_at  TIMESTAMP DEFAULT NOW(),
    updated_at  TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (assignee_id) REFERENCES users (id) ON DELETE SET NULL
);

-- Создание таблицы комментариев
CREATE TABLE comments
(
    id SERIAL PRIMARY KEY,
    task_id    INT  NOT NULL,
    user_id    INT  NOT NULL,
    content    TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- Индексы
CREATE INDEX idx_tasks_author_id ON tasks (author_id);
CREATE INDEX idx_tasks_assignee_id ON tasks (assignee_id);
CREATE INDEX idx_comments_task_id ON comments (task_id);

-- Добавление администратора по умолчанию (пароль нужно хешировать)
INSERT INTO users (email, password, role)
VALUES ('admin@example.com', '$2a$10$somehashedpassword', 'ADMIN');
