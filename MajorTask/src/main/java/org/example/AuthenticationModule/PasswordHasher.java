package org.example.AuthenticationModule;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

    public static String hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Convert bytes → hex string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing error", e);
        }
    }
}
