CREATE TABLE invoice (
    id SERIAL PRIMARY KEY,
    number VARCHAR(50) NOT NULL UNIQUE,
    date TIMESTAMP NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    exchange_rate DECIMAL(10, 4) NOT NULL,
    product VARCHAR(255) NOT NULL,
    quantity DECIMAL(10, 2) NOT NULL
);

CREATE TABLE payment (
    id SERIAL PRIMARY KEY,
    number VARCHAR(50) NOT NULL UNIQUE,
    date TIMESTAMP NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    employee VARCHAR(100) NOT NULL
);

CREATE TABLE payment_request (
    id SERIAL PRIMARY KEY,
    number VARCHAR(50) NOT NULL UNIQUE,
    date TIMESTAMP NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    contractor VARCHAR(100) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    exchange_rate DECIMAL(10, 4) NOT NULL,
    commission DECIMAL(10, 2) NOT NULL
);