CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks
(
    id              BIGSERIAL PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    description     VARCHAR(255) NULL,
    status          VARCHAR(255) NOT NULL,
    expiration_date TIMESTAMP    NULL
);

CREATE TABLE IF NOT EXISTS users_tasks
(
    user_id BIGSERIAL NOT NULL,
    task_id BIGSERIAL NOT NULL,
    PRIMARY KEY (user_id, task_id),
    constraint fk_users_tasks_users foreign key (user_id) references users (id) on delete cascade on update no action,
    constraint fk_users_tasks_tasks foreign key (task_id) references tasks (id) on delete cascade on update no action
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGSERIAL    NOT NULL,
    role    VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id, role),
    constraint fk_users_roles_users foreign key (user_id) references users (id) on delete cascade on update no action
);

CREATE TABLE IF NOT EXISTS tasks_images
(
    task_id BIGSERIAL    NOT NULL,
    image   VARCHAR(255) NOT NULL,
    constraint fk_tasks_images_tasks foreign key (task_id) references tasks (id) on delete cascade on update no action
);