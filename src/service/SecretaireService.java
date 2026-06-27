package service;

import dao.EtudiantDAO;
import dao.SecretaireDAO;
import model.Etudiant;

import java.util.List;
import java.util.Optional;

public class SecretaireService {
    private final SecretaireDAO secretaireDAO;
    private final EtudiantDAO etudiantDAO;

    public SecretaireService(EtudiantDAO etudiantDAO) {
        this.secretaireDAO = new SecretaireDAO();
        this.etudiantDAO = etudiantDAO;
    }

    public SecretaireDAO getSecretaireDAO() {
        return secretaireDAO;
    }

    public boolean ajouterEtudiant(String idSecretaire, String id, String nom, String prenom) {
        return etudiantDAO.ajouterEtudiant(idSecretaire, new Etudiant(id, nom, prenom));
    }

    public boolean supprimerEtudiant(String idSecretaire, String idEtudiant) {
        return etudiantDAO.supprimerEtudiant(idSecretaire, idEtudiant);
    }

    public Optional<Etudiant> rechercherEtudiant(String idSecretaire, String idEtudiant) {
        return etudiantDAO.rechercherEtudiant(idSecretaire, idEtudiant);
    }

    public List<Etudiant> listerEtudiants(String idSecretaire) {
        return etudiantDAO.listerEtudiants(idSecretaire);
    }
}
