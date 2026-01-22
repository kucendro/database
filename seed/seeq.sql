CREATE TABLE IF NOT EXISTS users (
 id CHAR(36) PRIMARY KEY UNIQUE,
 name VARCHAR(50),
 email VARCHAR(100) UNIQUE
);

INSERT IGNORE INTO users (id, name, email) VALUES
 ('cwijva8cvj', 'Alpha', 'alpha@example.com'),
 ('afvéienebvqb', 'Beta', 'beta@example.com'),
 ('ghkjsdfh23', 'Gamma', 'gamma@example.com');