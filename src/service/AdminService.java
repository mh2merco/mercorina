package service;

import dao.AdminDAO;
import model.Secretaire;

import java.util.List;
import java.util.Optional;

public class AdminService {
    private final AdminDAO adminDAO;

    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public boolean ajouterSecretaire(String id, String nom, String prenom) {
        return adminDAO.ajouterSecretaire(new Secretaire(id, nom, prenom));
    }

    public boolean supprimerSecretaire(String id) {
        return adminDAO.supprimerSecretaire(id);
    }

    public Optional<Secretaire> rechercherSecretaire(String id) {
        return adminDAO.rechercherSecretaire(id);
    }

    public List<Secretaire> listerSecretaires() {
        return adminDAO.listerSecretaires();
    }
}
