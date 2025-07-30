package org.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        try{
            /* 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/aggiunta_movimenti.fxml"));
            Parent root = loader.load();
            Button indietro = (Button) root.lookup("#btnIndietro");
            Button salva = (Button) root.lookup("#btnSalva");
            TextField  causale = (TextField ) root.lookup("#txtCasuale");
            TextField  importo = (TextField ) root.lookup("#txtImporto");
            ChoiceBox<TipiTransazioni> tipo = (ChoiceBox<TipiTransazioni>) root.lookup("#selTipo");
            tipo.getItems().setAll(TipiTransazioni.values());
            tipo.setValue(TipiTransazioni.ENTRATA);
            importo.textProperty().addListener(x -> {
                String val = importo.getText();
                try {
                    Float.parseFloat(val);
                } catch(Exception e){
                    importo.setText("0.0");
                }
            });
            indietro.setOnAction(x -> {
                stage.close();
            });
            salva.setOnAction(x -> {
                VBox box = new VBox();
                box.getChildren().add(new Label("Causale " + causale.getText()));
                box.getChildren().add(new Label("Importo " + importo.getText()));
                box.getChildren().add(new Label("Tipo " + tipo.getValue().toString()));
                Scene scene2 = new Scene(box);
                stage.setScene(scene2);
            });
            */
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/index.fxml"));
            Parent root = loader.load();
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

    public enum TipiTransazioni{
        ENTRATA,
        USCITA
    }
}
