CREATE TABLE IF NOT EXISTS "roles" (
    role_id SERIAL NOT NULL,
    name VARCHAR(20) NOT NULL UNIQUE,
    CONSTRAINT "pk_roles" PRIMARY KEY (role_id)
);
