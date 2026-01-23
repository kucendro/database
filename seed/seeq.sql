CREATE TABLE
    IF NOT EXISTS users (
        id INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
        name VARCHAR(50),
        email VARCHAR(100) UNIQUE
    );

INSERT
    IGNORE INTO users (id, name, email)
VALUES
    (1, 'Epstein', 'alpha@example.com'),
    (2, 'Diddy', 'beta@example.com'),
    (3, 'Netainahu', 'gamma@example.com');