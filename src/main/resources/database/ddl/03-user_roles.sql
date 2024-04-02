CREATE TABLE IF NOT EXISTS "user_roles" (
    id UUID DEFAULT uuid_generate_v4(),
    user_id int NOT NULL,
    role_id INT NOT NULL,
    CONSTRAINT "pk-roles_for_users" PRIMARY KEY (id),
    CONSTRAINT "fk-user-table-ref" FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT "fk-roles-table-ref" FOREIGN KEY (role_id) REFERENCES roles(role_id)
);
