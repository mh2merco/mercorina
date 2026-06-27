package dao;

import model.Etudiant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EtudiantDAO {
    private final Map<String, List<Etudiant>> etudiantsParSecretaire = new HashMap<>();

    public boolean ajouterEtudiant(String idSecretaire, Etudiant etudiant) {
        var etudiants = etudiantsParSecretaire.computeIfAbsent(idSecretaire, k -> new ArrayList<>());
        if (etudiants.stream().anyMatch(e -> e.getId().equals(etudiant.getId()))) {
            return false;
        }
        etudiants.add(etudiant);
        return true;
    }

    public boolean supprimerEtudiant(String idSecretaire, String idEtudiant) {
        var etudiants = etudiantsParSecretaire.get(idSecretaire);
        if (etudiants == null) {
            return false;
        }
        Optional<Etudiant> etudiant = etudiants.stream()
                .filter(e -> e.getId().equals(idEtudiant))
                .findFirst();
        if (etudiant.isPresent()) {
            etudiants.remove(etudiant.get());
            return true;
        }
        return false;
    }

    public Optional<Etudiant> rechercherEtudiant(String idSecretaire, String idEtudiant) {
        var etudiants = etudiantsParSecretaire.get(idSecretaire);
        if (etudiants == null) {
            return Optional.empty();
        }
        return etudiants.stream()
                .filter(e -> e.getId().equals(idEtudiant))
                .findFirst();
    }

    public List<Etudiant> listerEtudiants(String idSecretaire) {
        return new ArrayList<>(etudiantsParSecretaire.getOrDefault(idSecretaire, List.of()));
    }
}
