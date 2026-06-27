package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Admin;

public class AdminDAO {

    // =========================
    // LOGIN ADMIN
    // =========================
    public Optional<Admin> login(String login, String motdepasse) {

        String sql = "SELECT * FROM admin WHERE login=? AND motdepasse=?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);
            ps.setString(2, motdepasse);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(new Admin(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("login"),
                        rs.getString("motdepasse")
                ));
            }

        } catch (Exception e) {
            System.out.println("Erreur login admin : " + e.getMessage());
        }

        return Optional.empty();
    }

    // =========================
    // AJOUT ADMIN (optionnel)
    // =========================
    public boolean ajouter(Admin admin) {

        String sql = "INSERT INTO admin(nom, login, motdepasse) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, admin.getNom());
            ps.setString(2, admin.getLogin());
            ps.setString(3, admin.getMotdepasse());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erreur ajout admin : " + e.getMessage());
            return false;
        }
    }

    // =========================
    // LISTE ADMINS (optionnel)
    // =========================
    public List<Admin> lister() {

        List<Admin> list = new ArrayList<>();

        String sql = "SELECT * FROM admin";

        try (Connection conn = ConnectionDB.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Admin(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("login"),
                        rs.getString("motdepasse")
                ));
            }

        } catch (Exception e) {
            System.out.println("Erreur liste admin : " + e.getMessage());
        }

        return list;
    }
}