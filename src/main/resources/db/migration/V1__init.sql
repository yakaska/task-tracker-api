-- Создание таблицы пользователей
CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    email      VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    role       VARCHAR(50)         NOT NULL CHECK (role IN ('ADMIN', 'USER')),
    created_at TIMESTAMP DEFAULT NOW()
);

-- Создание таблицы задач
CREATE TABLE tasks
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    status      VARCHAR(50)  NOT NULL CHECK (status IN ('PENDING', 'IN_PROGRESS', 'COMPLETED')),
    priority    VARCHAR(50)  NOT NULL CHECK (priority IN ('HIGH', 'MEDIUM', 'LOW')),
    author_id   INT          NOT NULL,
    assignee_id INT,
    created_at  TIMESTAMP DEFAULT NOW(),
    updated_at  TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (assignee_id) REFERENCES users (id) ON DELETE SET NULL
);

-- Создание таблицы комментариев
CREATE TABLE comments
(
    id         SERIAL PRIMARY KEY,
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

-- Тестовые данные

INSERT INTO users (email, password, role)
VALUES ('valentin7petr@gmail.com', '$2y$10$YtmxI1XVdZVf3B4eKqY/o.CGOXSlXqrofgCnjqYR.28ncuCgeuHpu', 'ADMIN');

INSERT INTO tasks (title, description, status, priority, author_id, assignee_id, created_at, updated_at)
VALUES ('Task 1', 'Description for Task 1', 'PENDING', 'HIGH', 1, 1, NOW(), NOW()),
       ('Task 2', 'Description for Task 2', 'PENDING', 'MEDIUM', 1, 1, NOW(), NOW()),
       ('Task 3', 'Description for Task 3', 'IN_PROGRESS', 'LOW', 1, 1, NOW(), NOW()),
       ('Task 4', 'Description for Task 4', 'IN_PROGRESS', 'HIGH', 1, 1, NOW(), NOW()),
       ('Task 5', 'Description for Task 5', 'COMPLETED', 'MEDIUM', 1, 1, NOW(), NOW()),
       ('Task 6', 'Description for Task 6', 'PENDING', 'HIGH', 1, 1, NOW(), NOW()),
       ('Task 7', 'Description for Task 7', 'COMPLETED', 'LOW', 1, 1, NOW(), NOW()),
       ('Task 8', 'Description for Task 8', 'PENDING', 'MEDIUM', 1, 1, NOW(), NOW()),
       ('Task 9', 'Description for Task 9', 'IN_PROGRESS', 'HIGH', 1, 1, NOW(), NOW()),
       ('Task 10', 'Description for Task 10', 'IN_PROGRESS', 'MEDIUM', 1, 1, NOW(), NOW());
