CREATE TABLE admin (
    id VARCHAR(50) PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL
);

CREATE TABLE secretaire (
    id VARCHAR(50) PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL
);

CREATE TABLE etudiant (
    id VARCHAR(50) PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    id_secretaire VARCHAR(50),
    FOREIGN KEY (id_secretaire) REFERENCES secretaire(id)
);
