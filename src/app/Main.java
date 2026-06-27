package app;

import java.util.Scanner;
import service.AdminService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AdminService admin = new AdminService();

        while (true) {

            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1 - Ajouter secrétaire");
            System.out.println("2 - Supprimer secrétaire");
            System.out.println("3 - Lister secrétaires");
            System.out.println("0 - Quitter");

            String choix = sc.nextLine();

            switch (choix) {

                case "1":
                    System.out.print("Nom: ");
                    String nom = sc.nextLine();

                    System.out.print("Login: ");
                    String login = sc.nextLine();

                    System.out.print("MDP: ");
                    String mdp = sc.nextLine();

                    admin.ajouterSecretaire(nom, login, mdp);
                    System.out.println("Ajout OK");
                    break;

                case "2":
                    System.out.print("ID: ");
                    admin.supprimerSecretaire(Integer.parseInt(sc.nextLine()));
                    System.out.println("Supprimé");
                    break;

                case "3":
                    admin.listerSecretaires().forEach(System.out::println);
                    break;

                case "0":
                    return;
            }
        }
    }
}