package service;

import dao.SecretaireDAO;
import java.util.List;
import java.util.Optional;
import model.Secretaire;

public class AdminService {

    private SecretaireDAO dao = new SecretaireDAO();

    public boolean ajouterSecretaire(String nom, String login, String mdp) {
        return dao.ajouter(new Secretaire(0, nom, login, mdp));
    }

    public boolean supprimerSecretaire(int id) {
        return dao.supprimer(id);
    }

    public List<Secretaire> listerSecretaires() {
        return dao.lister();
    }

    public Optional<Secretaire> rechercherSecretaire(int id) {
        return dao.rechercher(id);
    }
}
