CREATE TABLE IF NOT EXISTS "products" (
    id SERIAL,
    user_id UUID NOT NULL,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT "pk-products" PRIMARY KEY (id)
);