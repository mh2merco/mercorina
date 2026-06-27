package model;

public class Etudiant {

    private String id;
    private String nom;

    public Etudiant(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Etudiant{id='" + id + "', nom='" + nom + "'}";
    }
}