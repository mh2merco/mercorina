CREATE DATABASE gestion_etudiant;
USE gestion_etudiant;

CREATE TABLE admin(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    login VARCHAR(50) UNIQUE NOT NULL,
    motdepasse VARCHAR(100) NOT NULL
);

CREATE TABLE secretaire(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    login VARCHAR(50) UNIQUE NOT NULL,
    motdepasse VARCHAR(100) NOT NULL
);

CREATE TABLE etudiant(
    matricule INT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    age INT,
    filiere VARCHAR(50)
);