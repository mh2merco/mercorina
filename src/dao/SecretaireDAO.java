package DAO;

import model.Secretaire;

import java.sql.*;
import java.util.*;

public class SecretaireDAO {

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
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public boolean ajouter(Secretaire s) {

        String sql = "INSERT INTO secretaire(nom, login, motdepasse) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getNom());
            ps.setString(2, s.getLogin());
            ps.setString(3, s.getMotdepasse());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Secretaire> login(String login, String mdp) {

        String sql = "SELECT * FROM secretaire WHERE login=? AND motdepasse=?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);
            ps.setString(2, mdp);

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
            e.printStackTrace();
        }

        return Optional.empty();
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
            e.printStackTrace();
        }

        return list;
    }

    public boolean supprimer(int id) {

        String sql = "DELETE FROM secretaire WHERE id=?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}