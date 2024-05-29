package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HelloController extends AnchorPane {

    @FXML
    public void app() {


        //Weather.sendRequest();
//        User.setText(nbp.sendRequest("USD", "today").getMid().toString());
    }
    @FXML
    public void register(ActionEvent actionEvent) {

//        Stage stage = new Stage();
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
//        fxmlLoader.setRoot(this);
//        Scene scene = null;
//        try {
//            scene = new Scene(fxmlLoader.load(), 320, 240);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        stage.setTitle("register");
//        stage.setScene(scene);
//        stage.show();
        RegisterController registerController = new RegisterController();

    }


    /*@FXML
    protected void onCreateChildButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("child-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("CHILD");
        stage.setScene(scene);
        stage.show();
        ChildController childController = fxmlLoader.getController();
        this.children.add(childController);
        childController.setParent(this);

    }*/
}


