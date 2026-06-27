package dao;

import model.Secretaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SecretaireDAO {
    private final List<Secretaire> secretaires = new ArrayList<>();

    public boolean ajouterSecretaire(Secretaire secretaire) {
        if (rechercherSecretaire(secretaire.getId()).isPresent()) {
            return false;
        }
        secretaires.add(secretaire);
        return true;
    }

    public boolean supprimerSecretaire(String id) {
        Optional<Secretaire> secretaire = rechercherSecretaire(id);
        if (secretaire.isPresent()) {
            secretaires.remove(secretaire.get());
            return true;
        }
        return false;
    }

    public Optional<Secretaire> rechercherSecretaire(String id) {
        return secretaires.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    public List<Secretaire> listerSecretaires() {
        return new ArrayList<>(secretaires);
    }
}
