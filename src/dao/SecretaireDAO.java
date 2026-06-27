package dao;

import java.sql.*;
import java.util.*;
import model.Secretaire;

public class SecretaireDAO {

    public boolean ajouter(Secretaire s) {
        String sql = "INSERT INTO secretaire(nom, login, motdepasse) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getNom());
            ps.setString(2, s.getLogin());
            ps.setString(3, s.getMotdepasse());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erreur ajout secrétaire : " + e.getMessage());
            return false;
        }
    }

    public boolean supprimer(int id) {
        String sql = "DELETE FROM secretaire WHERE id=?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erreur suppression : " + e.getMessage());
            return false;
        }
    }

    public List<Secretaire> lister() {
        List<Secretaire> list = new ArrayList<>();
        String sql = "SELECT * FROM secretaire";

        try (Connection conn = ConnectionDB.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Secretaire(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("login"),
                        rs.getString("motdepasse")
                ));
            }

        } catch (Exception e) {
            System.out.println("Erreur liste : " + e.getMessage());
        }

        return list;
    }

    public Optional<Secretaire> rechercher(int id) {
        String sql = "SELECT * FROM secretaire WHERE id=?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(new Secretaire(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("login"),
                        rs.getString("motdepasse")
                ));
            }

        } catch (Exception e) {
            System.out.println("Erreur recherche : " + e.getMessage());
        }

        return Optional.empty();
    }
}