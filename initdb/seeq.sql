CREATE TABLE IF NOT EXISTS users (
 id INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(50),
 email VARCHAR(100) UNIQUE
);

INSERT IGNORE INTO users (name, email) VALUES
 ('Alpha', 'alpha@example.com'),
 ('Beta', 'beta@example.com'),
 ('Gamma', 'gamma@example.com');