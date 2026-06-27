public class Etudiant {
    private String id;
    private String nom;
    private String prenom;

    public Etudiant(String id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    @Override
    public String toString() {
        return "Etudiant{id='" + id + "', nom='" + nom + "', prenom='" + prenom + "'}";
    }
}
