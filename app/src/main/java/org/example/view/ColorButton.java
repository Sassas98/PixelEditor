package org.example.view;

import javafx.scene.Parent;
import javafx.scene.control.Button;

public class ColorButton {

    public ColorButton(Parent root, String id, Runnable action, String color){
        Button btn = (Button) root.lookup("#" +  id);
        btn.setOnAction(x -> action.run());
        btn.setStyle("-fx-background-color: " + color + ";");
    }

}
