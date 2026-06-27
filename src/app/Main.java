package app;

import DAO.AdminDAO;
import java.util.Optional;
import java.util.Scanner;
import model.Admin;

public class Main {

    public static void main(String[] args) {
<<<<<<< Updated upstream
=======
        Scanner scanner = new Scanner(System.in);
        EtudiantService etudiantService = new EtudiantService();
        SecretaireService secretaireService = new SecretaireService(etudiantService.getEtudiantDAO());
        AdminService adminService = new AdminService(new dao.AdminDAO(secretaireService.getSecretaireDAO()));
>>>>>>> Stashed changes

        Scanner sc = new Scanner(System.in);
        AdminDAO dao = new AdminDAO();

        System.out.println("=== LOGIN ADMIN ===");

        System.out.print("Login : ");
        String login = sc.nextLine();

        System.out.print("Mot de passe : ");
        String mdp = sc.nextLine();

        Optional<Admin> admin = dao.login(login, mdp);

        if (admin.isPresent()) {

            System.out.println("\n✔ Connexion réussie");
            System.out.println("Bienvenue " + admin.get().getNom());

            menuAdmin(sc);

        } else {
            System.out.println("\n❌ Login ou mot de passe incorrect");
        }
    }

    private static void menuAdmin(Scanner sc) {

        System.out.println("\n=== MENU ADMIN ===");

        while (true) {

            System.out.println("\n1 - Ajouter secrétaire");
            System.out.println("2 - Supprimer secrétaire");
            System.out.println("3 - Lister secrétaires");
            System.out.println("0 - Quitter");

            System.out.print("Choix : ");
            String choix = sc.nextLine();

            switch (choix) {

                case "1":
                    System.out.println("Ajouter secrétaire...");
                    // appel service ici
                    break;

                case "2":
                    System.out.println("Supprimer secrétaire...");
                    // appel service ici
                    break;

                case "3":
                    System.out.println("Lister secrétaires...");
                    // appel service ici
                    break;

                case "0":
                    System.out.println("Au revoir");
                    return;

                default:
                    System.out.println("Choix invalide");
            }
        }
    }
}