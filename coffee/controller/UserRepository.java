package coffee.controller;

import java.io.*;

public class UserRepository {
    private static final String FILE_PATH = "users.txt";

    //======================Save new user (append to file)============================================

    public static void saveUser(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(username + ";" + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ============================Checks if user and password exist===============================

    public static boolean validateUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // File does not exist yet, returns false
        }
        return false;
    }

    // ==================(Optional) Checks if a user already exists===================================

    public static boolean userExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 1 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // File does not exist yet, returns false
        }
        return false;
    }
}


