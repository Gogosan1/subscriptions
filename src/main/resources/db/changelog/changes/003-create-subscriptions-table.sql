CREATE TABLE IF NOT EXISTS sub.subscription (
    id UUID PRIMARY KEY,
    user_login VARCHAR(50) NOT NULL REFERENCES sub.users(login) ON DELETE RESTRICT,
    type VARCHAR(50) NOT NULL,
    expired_date_time TIMESTAMPTZ
);