package service;

import java.util.List;
import java.util.Optional;

import DAO.SecretaireDAO;
import model.Secretaire;

public class AdminService {

    private SecretaireDAO dao = new SecretaireDAO();

    public boolean ajouterSecretaire(String nom, String login, String mdp) {
        try {
            if (nom == null || nom.isEmpty() ||
                login == null || login.isEmpty() ||
                mdp == null || mdp.isEmpty()) {
                System.out.println("Erreur : champs vides");
                return false;
            }

            return dao.ajouter(new Secretaire(0, nom, login, mdp));

        } catch (Exception e) {
            System.out.println("Erreur ajout secrétaire :");
            e.printStackTrace();
            return false;
        }
    }

    public boolean supprimerSecretaire(int id) {
        try {
            return dao.supprimer(id);
        } catch (Exception e) {
            System.out.println("Erreur suppression secrétaire :");
            e.printStackTrace();
            return false;
        }
    }

    public List<Secretaire> listerSecretaires() {
        try {
            return dao.lister();
        } catch (Exception e) {
            System.out.println("Erreur listage secrétaires :");
            e.printStackTrace();
            return null;
        }
    }

    public Optional<Secretaire> rechercherSecretaire(int id) {
        try {
            return dao.rechercher(id);
        } catch (Exception e) {
            System.out.println("Erreur recherche secrétaire :");
            e.printStackTrace();
            return Optional.empty();
        }
    }
}