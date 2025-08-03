package org.example;

import java.io.IOException;

import org.example.controller.SimpleColors2D32PixelGetter;
import org.example.controller.SimpleColors2D32PixelSetter;
import org.example.model.entity.Pixel;
import org.example.model.persistence.Context;
import org.example.model.persistence.HibernateContext;
import org.example.view.EditorViewHandler;

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
            setUpFromRoot(root);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("");
            stage.setResizable(false);
            stage.show();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    private void setUpFromRoot(Parent root){
        Context<Pixel> db = new HibernateContext<Pixel>(Pixel.class);
        SimpleColors2D32PixelGetter getter = new SimpleColors2D32PixelGetter(db);
        EditorViewHandler handler = new EditorViewHandler(root, getter, (p, c) -> {
            HibernateContext<Pixel> ctx = new HibernateContext<Pixel>(Pixel.class);
            SimpleColors2D32PixelSetter setter = new SimpleColors2D32PixelSetter(ctx);
            setter.setPixelColor(p, c);
            setter.dispose();
        });
        handler.setUpColorButtons();
        handler.setUpGrid();
        getter.dispose();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
