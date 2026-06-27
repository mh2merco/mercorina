import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Secretaire {
    private String nom;
    private List<Etudiant> etudiants;

    public Secretaire(String nom) {
        this.nom = nom;
        this.etudiants = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public boolean ajouterEtudiant(Etudiant etudiant) {
        if (rechercherEtudiant(etudiant.getId()).isPresent()) {
            return false;
        }
        etudiants.add(etudiant);
        return true;
    }

    public boolean supprimerEtudiant(String id) {
        Optional<Etudiant> etudiant = rechercherEtudiant(id);
        if (etudiant.isPresent()) {
            etudiants.remove(etudiant.get());
            return true;
        }
        return false;
    }

    public Optional<Etudiant> rechercherEtudiant(String id) {
        return etudiants.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public List<Etudiant> listerEtudiants() {
        return new ArrayList<>(etudiants);
    }
}
