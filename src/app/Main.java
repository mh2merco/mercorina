package app;

import java.util.Optional;
import java.util.Scanner;

import DAO.AdminDAO;
import DAO.SecretaireDAO;

import model.Admin;
import model.Secretaire;

import service.AdminService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== SYSTEME DE GESTION ===");
            System.out.println("1 - Connexion Admin");
            System.out.println("2 - Connexion Secretaire");
            System.out.println("0 - Quitter");

            System.out.print("Choix : ");
            String choix = sc.nextLine();

            switch (choix) {

                case "1":
                    loginAdmin(sc);
                    break;

                case "2":
                    loginSecretaire(sc);
                    break;

                case "0":
                    System.out.println("Au revoir");
                    sc.close();
                    return;

                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    // ================= ADMIN =================
    private static void loginAdmin(Scanner sc) {

        AdminDAO dao = new AdminDAO();

        System.out.println("\n=== LOGIN ADMIN ===");

        System.out.print("Login : ");
        String login = sc.nextLine();

        System.out.print("Mot de passe : ");
        String mdp = sc.nextLine();

        Optional<Admin> admin = dao.login(login, mdp);

        if (admin.isPresent()) {
            System.out.println("✔ Admin connecté");
            menuAdmin(sc);
        } else {
            System.out.println("❌ Login admin incorrect");
        }
    }

    // ================= SECRETAIRE =================
    private static void loginSecretaire(Scanner sc) {

        SecretaireDAO dao = new SecretaireDAO();

        System.out.println("\n=== LOGIN SECRETAIRE ===");

        System.out.print("Login : ");
        String login = sc.nextLine();

        System.out.print("Mot de passe : ");
        String mdp = sc.nextLine();

        Optional<Secretaire> sec = dao.login(login, mdp);

        if (sec.isPresent()) {
            System.out.println("✔ Secretaire connecté");
            menuSecretaire(sc, sec.get());
        } else {
            System.out.println("❌ Login secrétaire incorrect");
        }
    }

    // ================= MENU ADMIN =================
    private static void menuAdmin(Scanner sc) {

        AdminService service = new AdminService();

        while (true) {

            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1 - Ajouter secrétaire");
            System.out.println("2 - Supprimer secrétaire");
            System.out.println("3 - Lister secrétaires");
            System.out.println("0 - Déconnexion");

            System.out.print("Choix : ");
            String choix = sc.nextLine();

            switch (choix) {

                case "1":
                    System.out.print("Nom : ");
                    String nom = sc.nextLine();

                    System.out.print("Login : ");
                    String login = sc.nextLine();

                    System.out.print("Mot de passe : ");
                    String mdp = sc.nextLine();

                    System.out.println(
                        service.ajouterSecretaire(nom, login, mdp)
                        ? "✔ Ajout OK"
                        : "❌ Erreur"
                    );
                    break;

                case "2":
                    System.out.print("ID : ");
                    int id = Integer.parseInt(sc.nextLine());

                    System.out.println(
                        service.supprimerSecretaire(id)
                        ? "✔ Supprimé"
                        : "❌ Erreur"
                    );
                    break;

                case "3":
                    service.listerSecretaires().forEach(System.out::println);
                    break;

                case "0":
                    return;

                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    // ================= MENU SECRETAIRE =================
    private static void menuSecretaire(Scanner sc, Secretaire sec) {

        DAO.EtudiantDAO dao = new DAO.EtudiantDAO();

        while (true) {

            System.out.println("\n=== MENU SECRETAIRE ===");
            System.out.println("1 - Ajouter étudiant");
            System.out.println("2 - Supprimer étudiant");
            System.out.println("3 - Lister étudiants");
            System.out.println("4 - Rechercher étudiant");
            System.out.println("0 - Déconnexion");

            System.out.print("Choix : ");
            String choix = sc.nextLine();

            switch (choix) {

                case "1":
                    System.out.print("ID : ");
                    String id = sc.nextLine();

                    System.out.print("Nom : ");
                    String nom = sc.nextLine();

                    dao.ajouter(sec.getLogin(), new model.Etudiant(id, nom));
                    System.out.println("✔ Ajout étudiant OK");
                    break;

                case "2":
                    System.out.print("ID : ");
                    dao.supprimer(sec.getLogin(), sc.nextLine());
                    break;

                case "3":
                    dao.lister(sec.getLogin()).forEach(System.out::println);
                    break;

                case "4":
                    System.out.print("ID : ");
                    System.out.println(
                        dao.rechercherEtudiant(sec.getLogin(), sc.nextLine())
                    );
                    break;

                case "0":
                    return;

                default:
                    System.out.println("Choix invalide");
            }
        }
    }
}