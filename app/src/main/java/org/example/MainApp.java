package org.example;

import org.example.model.MyEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static EntityManagerFactory emf;

    @Override
    public void start(Stage primaryStage) {
        // Avvio JavaFX
        Label label = new Label("Hello JavaFX + Hibernate + H2!");
        primaryStage.setScene(new Scene(label, 400, 200));
        primaryStage.setTitle("Demo App");
        primaryStage.show();

        // Salvataggio entità con Hibernate
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        MyEntity entity = new MyEntity("TestName");
        em.persist(entity);
        em.getTransaction().commit();
        em.close();

        System.out.println("Entity saved: " + entity);
    }

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("myPU");
    }

    @Override
    public void stop() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
