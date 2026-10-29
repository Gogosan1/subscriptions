INSERT INTO sub.users (login)
 VALUES
('pavel'),
('ivan');

INSERT INTO sub.subscription (id, user_login, type, expired_date_time)
VALUES
(gen_random_uuid(), 'pavel', 'PAID', '2026-11-30 23:59:59+03'),
(gen_random_uuid(), 'pavel', 'FREE', NULL),
(gen_random_uuid(), 'ivan', 'FREE', NULL);