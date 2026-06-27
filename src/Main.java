import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin("admin");

        System.out.println("=== Gestion des secretaires et des etudiants ===");
        boolean quitter = false;

        while (!quitter) {
            System.out.println("\nMenu principal :");
            System.out.println("1 - Gérer les secrétaires (admin)");
            System.out.println("2 - Gérer les étudiants (secrétaire)");
            System.out.println("0 - Quitter");
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1" -> gererSecretaires(admin, scanner);
                case "2" -> gererEtudiants(admin, scanner);
                case "0" -> quitter = true;
                default -> System.out.println("Choix invalide. Réessayez.");
            }
        }
        System.out.println("Fin du programme.");
        scanner.close();
    }

    private static void gererSecretaires(Admin admin, Scanner scanner) {
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
                    System.out.print("Nom de la secrétaire : ");
                    String nom = scanner.nextLine();
                    if (admin.ajouterSecretaire(new Secretaire(nom))) {
                        System.out.println("Secrétaire ajoutée.");
                    } else {
                        System.out.println("Une secrétaire avec ce nom existe déjà.");
                    }
                }
                case "2" -> {
                    System.out.print("Nom de la secrétaire à supprimer : ");
                    String nom = scanner.nextLine();
                    if (admin.supprimerSecretaire(nom)) {
                        System.out.println("Secrétaire supprimée.");
                    } else {
                        System.out.println("Secrétaire introuvable.");
                    }
                }
                case "3" -> {
                    System.out.println("Liste des secrétaires :");
                    if (admin.listerSecretaires().isEmpty()) {
                        System.out.println("Aucune secrétaire enregistrée.");
                    } else {
                        admin.listerSecretaires().forEach(s -> System.out.println("- " + s.getNom()));
                    }
                }
                case "0" -> retour = true;
                default -> System.out.println("Choix invalide. Réessayez.");
            }
        }
    }

    private static void gererEtudiants(Admin admin, Scanner scanner) {
        System.out.print("Nom de la secrétaire qui opère : ");
        String nomSecretaire = scanner.nextLine();
        var secretaireOpt = admin.rechercherSecretaire(nomSecretaire);
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
                    if (secretaire.ajouterEtudiant(new Etudiant(id, nom, prenom))) {
                        System.out.println("Étudiant ajouté.");
                    } else {
                        System.out.println("Un étudiant avec ce même ID existe déjà.");
                    }
                }
                case "2" -> {
                    System.out.print("ID de l'étudiant à supprimer : ");
                    String id = scanner.nextLine();
                    if (secretaire.supprimerEtudiant(id)) {
                        System.out.println("Étudiant supprimé.");
                    } else {
                        System.out.println("Étudiant introuvable.");
                    }
                }
                case "3" -> {
                    System.out.print("ID de l'étudiant à rechercher : ");
                    String id = scanner.nextLine();
                    var etudiantOpt = secretaire.rechercherEtudiant(id);
                    if (etudiantOpt.isPresent()) {
                        System.out.println("Étudiant trouvé : " + etudiantOpt.get());
                    } else {
                        System.out.println("Aucun étudiant trouvé avec cet ID.");
                    }
                }
                case "4" -> {
                    System.out.println("Liste des étudiants :");
                    if (secretaire.listerEtudiants().isEmpty()) {
                        System.out.println("Aucun étudiant enregistré.");
                    } else {
                        secretaire.listerEtudiants().forEach(System.out::println);
                    }
                }
                case "0" -> retour = true;
                default -> System.out.println("Choix invalide. Réessayez.");
            }
        }
    }
}
