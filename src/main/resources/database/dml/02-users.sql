INSERT INTO users (username, password, user_id, email, enabled)
SELECT 'admin',
       '$2a$10$5FXtEyzSJ0ZlftvoGcmSruOtqZE6lx.TA9Qu2KN5LYpucttGik0yq',
       '6b390a3c-5896-4c3e-8ac9-6f0e325b011f',
       'admin@admin.com',
       true
WHERE NOT EXISTS (SELECT email FROM users WHERE email = 'admin@admin.com');

INSERT INTO user_roles (user_id, role_id)
SELECT 1,
       1
WHERE NOT EXISTS( SELECT user_id, role_id FROM user_roles WHERE user_id = 1 AND role_id = 1);