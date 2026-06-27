import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Admin {
    private String nom;
    private List<Secretaire> secretaires;

    public Admin(String nom) {
        this.nom = nom;
        this.secretaires = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public boolean ajouterSecretaire(Secretaire secretaire) {
        if (rechercherSecretaire(secretaire.getNom()).isPresent()) {
            return false;
        }
        secretaires.add(secretaire);
        return true;
    }

    public boolean supprimerSecretaire(String nomSecretaire) {
        Optional<Secretaire> secretaire = rechercherSecretaire(nomSecretaire);
        if (secretaire.isPresent()) {
            secretaires.remove(secretaire.get());
            return true;
        }
        return false;
    }

    public Optional<Secretaire> rechercherSecretaire(String nomSecretaire) {
        return secretaires.stream()
                .filter(s -> s.getNom().equalsIgnoreCase(nomSecretaire))
                .findFirst();
    }

    public List<Secretaire> listerSecretaires() {
        return new ArrayList<>(secretaires);
    }
}
