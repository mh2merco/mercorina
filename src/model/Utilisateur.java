package model;

public abstract class Utilisateur {
    private final String id;
    private final String nom;
    private final String prenom;

    public Utilisateur(String id, String nom, String prenom) {
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
        return id + " - " + nom + " " + prenom;
    }
}
