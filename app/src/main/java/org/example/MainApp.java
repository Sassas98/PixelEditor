package org.example;

import org.example.model.MyEntity;
import org.example.model.persistence.HibernateContext;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        HibernateContext<MyEntity> db = new HibernateContext<MyEntity>(MyEntity.class);
        db.save(new MyEntity("TestName2"));
        db.load().stream().forEach(System.out::println);
        db.dispose();
        Label label = new Label("Hello JavaFX + Hibernate + H2!");
        primaryStage.setScene(new Scene(label, 400, 200));
        primaryStage.setTitle("Demo App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
