package DAO;

import model.Etudiant;

import java.util.*;

public class EtudiantDAO {

    private final Map<String, List<Etudiant>> data = new HashMap<>();

    // AJOUT
    public boolean ajouter(String loginSecretaire, Etudiant etu) {
        List<Etudiant> list =
                data.computeIfAbsent(loginSecretaire, k -> new ArrayList<>());
        return list.add(etu);
    }

    // SUPPRIMER
    public boolean supprimer(String loginSecretaire, String id) {
        List<Etudiant> list = data.get(loginSecretaire);
        if (list == null) return false;
        return list.removeIf(e -> e.getId().equals(id));
    }

    // RECHERCHER
    public Optional<Etudiant> rechercherEtudiant(String loginSecretaire, String id) {
        List<Etudiant> list = data.get(loginSecretaire);
        if (list == null) return Optional.empty();

        return list.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    // LISTER
    public List<Etudiant> lister(String loginSecretaire) {
        return new ArrayList<>(
                data.getOrDefault(loginSecretaire, new ArrayList<>())
        );
    }
}