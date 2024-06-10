package features.user.datasource;
import features.user.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static final String DATABASE_URL = "jdbc:sqlite:biblioteca.db";

    // Criar User BD
    public static void createDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Usuario ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT NOT NULL,"
                + "senha TEXT NOT NULL,"
                + "cargo TEXT NOT NULL"
                + ");";


        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Pegar todos usuarios do BD
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String selectSQL = "SELECT * FROM users";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("senha"),
                        rs.getString("cargo")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    // Adicionar usuarios no BD
    public static void addUser(String name, String senha, String cargo) {
        String insertSQL = "INSERT INTO users (name, senha, cargo) VALUES ( ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, senha);
            pstmt.setString(3, cargo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Deletar usuarios do BD
    public static void deleteUser(String name) {
        String deleteSQL = "DELETE FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Validacao de usuario no BD
    public static boolean validateUser(String name, String senha) {
        String selectSQL = "SELECT * FROM users WHERE name = ? AND senha = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
            PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery();

            // Se houver algum resultado, significa que o usuário e a senha estão corretos
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Pegar o cargo do usuario que acessou
    public static String getUserCargo(String name) {
        String cargo = null;
        String selectSQL = "SELECT cargo FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                cargo = rs.getString("cargo");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cargo;
    }
}