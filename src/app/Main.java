package app;

import model.Secretaire;
import service.AdminService;
import service.SecretaireService;
import service.EtudiantService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EtudiantService etudiantService = new EtudiantService();
        SecretaireService secretaireService = new SecretaireService(etudiantService);
        AdminService adminService = new AdminService(secretaireService.getSecretaireDAO());

        boolean quitter = false;
        System.out.println("=== Gestion administrateur / secrétaire / étudiant ===");

        while (!quitter) {
            System.out.println("\nMenu principal :");
            System.out.println("1 - Gérer les secrétaires (admin)");
            System.out.println("2 - Gérer les étudiants (secrétaire)");
            System.out.println("0 - Quitter");
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1" -> gererSecretaires(adminService, scanner);
                case "2" -> gererEtudiants(adminService, secretaireService, scanner);
                case "0" -> quitter = true;
                default -> System.out.println("Choix invalide. Réessayez.");
            }
        }

        System.out.println("Fin du programme.");
        scanner.close();
    }

    private static void gererSecretaires(AdminService adminService, Scanner scanner) {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n--- Gestion des secrétaires ---");
            System.out.println("1 - Ajouter une secrétaire");
            System.out.println("2 - Supprimer une secrétaire");
            System.out.println("3 - Afficher la liste des secrétaires");
            System.out.println("0 - Retour");
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1" -> {
                    System.out.print("ID de la secrétaire : ");
                    String id = scanner.nextLine();
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    if (adminService.ajouterSecretaire(id, nom, prenom)) {
                        System.out.println("Secrétaire ajoutée.");
                    } else {
                        System.out.println("Une secrétaire avec cet ID existe déjà.");
                    }
                }
                case "2" -> {
                    System.out.print("ID de la secrétaire à supprimer : ");
                    String id = scanner.nextLine();
                    if (adminService.supprimerSecretaire(id)) {
                        System.out.println("Secrétaire supprimée.");
                    } else {
                        System.out.println("Secrétaire introuvable.");
                    }
                }
                case "3" -> {
                    System.out.println("Liste des secrétaires :");
                    var secretaires = adminService.listerSecretaires();
                    if (secretaires.isEmpty()) {
                        System.out.println("Aucune secrétaire enregistrée.");
                    } else {
                        secretaires.forEach(s -> System.out.println("- " + s));
                    }
                }
                case "0" -> retour = true;
                default -> System.out.println("Choix invalide. Réessayez.");
            }
        }
    }

    private static void gererEtudiants(AdminService adminService, SecretaireService secretaireService, Scanner scanner) {
        System.out.print("ID de la secrétaire qui opère : ");
        String secretaireId = scanner.nextLine();
        var secretaireOpt = adminService.rechercherSecretaire(secretaireId);
        if (secretaireOpt.isEmpty()) {
            System.out.println("Secrétaire introuvable. Retour au menu principal.");
            return;
        }
        Secretaire secretaire = secretaireOpt.get();

        boolean retour = false;
        while (!retour) {
            System.out.println("\n--- Gestion des étudiants par " + secretaire.getNom() + " ---");
            System.out.println("1 - Ajouter un étudiant");
            System.out.println("2 - Supprimer un étudiant");
            System.out.println("3 - Rechercher un étudiant");
            System.out.println("4 - Afficher la liste des étudiants");
            System.out.println("0 - Retour");
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1" -> {
                    System.out.print("ID de l'étudiant : ");
                    String id = scanner.nextLine();
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    if (secretaireService.ajouterEtudiant(secretaireId, id, nom, prenom)) {
                        System.out.println("Étudiant ajouté.");
                    } else {
                        System.out.println("Un étudiant avec cet ID existe déjà pour cette secrétaire.");
                    }
                }
                case "2" -> {
                    System.out.print("ID de l'étudiant à supprimer : ");
                    String id = scanner.nextLine();
                    if (secretaireService.supprimerEtudiant(secretaireId, id)) {
                        System.out.println("Étudiant supprimé.");
                    } else {
                        System.out.println("Étudiant introuvable.");
                    }
                }
                case "3" -> {
                    System.out.print("ID de l'étudiant à rechercher : ");
                    String id = scanner.nextLine();
                    var etudiantOpt = secretaireService.rechercherEtudiant(secretaireId, id);
                    if (etudiantOpt.isPresent()) {
                        System.out.println("Étudiant trouvé : " + etudiantOpt.get());
                    } else {
                        System.out.println("Aucun étudiant trouvé avec cet ID.");
                    }
                }
                case "4" -> {
                    System.out.println("Liste des étudiants :");
                    var etudiants = secretaireService.listerEtudiants(secretaireId);
                    if (etudiants.isEmpty()) {
                        System.out.println("Aucun étudiant enregistré pour cette secrétaire.");
                    } else {
                        etudiants.forEach(System.out::println);
                    }
                }
                case "0" -> retour = true;
                default -> System.out.println("Choix invalide. Réessayez.");
            }
        }
    }
}
