package dao;

import model.Secretaire;

import java.util.List;
import java.util.Optional;

public class AdminDAO {
    private final SecretaireDAO secretaireDAO;

    public AdminDAO(SecretaireDAO secretaireDAO) {
        this.secretaireDAO = secretaireDAO;
    }

    public boolean ajouterSecretaire(Secretaire secretaire) {
        return secretaireDAO.ajouterSecretaire(secretaire);
    }

    public boolean supprimerSecretaire(String id) {
        return secretaireDAO.supprimerSecretaire(id);
    }

    public Optional<Secretaire> rechercherSecretaire(String id) {
        return secretaireDAO.rechercherSecretaire(id);
    }

    public List<Secretaire> listerSecretaires() {
        return secretaireDAO.listerSecretaires();
    }
}
