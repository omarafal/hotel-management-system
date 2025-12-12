package org.example.AuthenticationModule;

import java.sql.*;

public class AuthManager {

    private User currentUser;

    public boolean login(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password_hash");
                String inputHash = PasswordHasher.hash(password);

                if (storedHash.equals(inputHash)) {
                    currentUser = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            storedHash,
                            rs.getString("role")
                    );
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public String requestPasswordReset(String username) {
        String token = TokenGenerator.generate();
        String expiry = String.valueOf(System.currentTimeMillis() + 15 * 60 * 1000); // 15 mins

        String sql = "UPDATE Users SET reset_token=?, reset_expiry=? WHERE username=?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, token);
            stmt.setString(2, expiry);
            stmt.setString(3, username);
            stmt.executeUpdate();

            return token;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean resetPassword(String token, String newPassword) {
        String sqlFind = "SELECT * FROM Users WHERE reset_token=?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sqlFind)) {

            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long expiry = Long.parseLong(rs.getString("reset_expiry"));

                if (System.currentTimeMillis() > expiry)
                    return false; // Token expired

                String hashed = PasswordHasher.hash(newPassword);

                String sqlUpdate = """
                UPDATE Users 
                SET password_hash=?, reset_token=NULL, reset_expiry=NULL 
                WHERE reset_token=?
                """;

                try (PreparedStatement updateStmt = conn.prepareStatement(sqlUpdate)) {
                    updateStmt.setString(1, hashed);
                    updateStmt.setString(2, token);
                    updateStmt.executeUpdate();
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }



    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
