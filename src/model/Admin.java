package model;

public class Admin {

    private int id;
    private String nom;
    private String login;
    private String motdepasse;

    // Constructeur complet
    public Admin(int id, String nom, String login, String motdepasse) {
        this.id = id;
        this.nom = nom;
        this.login = login;
        this.motdepasse = motdepasse;
    }

    // Constructeur vide (utile parfois pour JDBC)
    public Admin() {}

    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getLogin() {
        return login;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    // Setters (optionnels mais utiles)
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    @Override
    public String toString() {
        return "Admin{id=" + id +
                ", nom='" + nom + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}