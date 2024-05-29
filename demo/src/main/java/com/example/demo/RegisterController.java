package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterController extends AnchorPane {

    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public RegisterController()
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 320, 240);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("register");
        stage.setScene(scene);
        stage.show();
    }

    private static final String USERS_FILE = "users.txt";

    @FXML
    public void OnclickSubmit(ActionEvent submit) {
        String username1 = this.username.getText();
        String password1 = this.password.getText();

        try {
            registerUser(username1, password1);
            System.out.println("User registered successfully!");
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println("Error during registration: " + e.getMessage());
        }

    }

    private static void registerUser(String username, String password) throws IOException, NoSuchAlgorithmException {
        String hashedPassword = hashPassword(password);

        // Format: username:hashedPassword
        String userEntry = username + ":" + hashedPassword;

        // Write the user entry to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(userEntry);
            writer.newLine();
        }
    }

    private static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());
        return bytesToHex(hash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
} // PENIS PENIS PENIS PENIS PENIS PENIS PENIS PENIS

