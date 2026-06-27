package model;

public class Secretaire {

    private int id;
    private String nom;
    private String login;
    private String motdepasse;

    public Secretaire(int id, String nom, String login, String motdepasse) {
        this.id = id;
        this.nom = nom;
        this.login = login;
        this.motdepasse = motdepasse;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getLogin() { return login; }
    public String getMotdepasse() { return motdepasse; }

    @Override
    public String toString() {
        return id + " - " + nom + " (" + login + ")";
    }
}