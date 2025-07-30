package org.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/index.fxml"));
            Parent root = loader.load();
            //Button btn = (Button) root.lookup("#button");
            //Label label = (Label) root.lookup("#number");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("");
            stage.setResizable(false);
            stage.show();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
