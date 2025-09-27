INSERT INTO users (name, username, password, confirmation_token, is_enabled)
VALUES ('Александр Иванов', 'aivano4v@gmail.com', '$2a$12$aI8fLQlw6pUSOhYpylGVdOm6NL6eUGlugvr3hZqmpFiqvoq/k4u2K', '7ef88f8a-962b-4557-a686-4ddab27001e3', true),
       ('Мария Петрова', 'mpetr4ova@gmail.com', '$2a$12$aI8fLQlw6pUSOhYpylGVdOm6NL6eUGlugvr3hZqmpFiqvoq/k4u2K', '7ef88f8a-962b-4557-a686-4ddab27001e3', true);

INSERT INTO users_roles (user_id, role)
VALUES (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');

INSERT INTO tasks (title, description, status, expiration_date)
VALUES ('Подготовить отчет за январь', 'Собрать данные и подготовить PDF-отчет', 'DONE', '2025-02-05 18:00:00'),
       ('Обновить сайт компании', NULL, 'IN_PROGRESS', '2025-01-20 12:00:00'),
       ('Тестирование функционала оплаты', 'Проверка edge-case и интеграций', 'DONE', '2025-01-25 23:59:00'),
       ('Подготовка презентации инвесторам', 'Слайды + демонстрация продукта', 'TODO', '2024-12-15 09:00:00');

INSERT INTO users_tasks (user_id, task_id)
VALUES (1, 2),
       (2, 2),
       (2, 3),
       (1, 4);