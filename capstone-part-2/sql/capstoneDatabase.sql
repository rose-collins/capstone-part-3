CREATE DATABASE capstone;

USE capstone;

CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL,
    userType ENUM('farmer', 'customer') NOT NULL,
    PRIMARY KEY (id)
);