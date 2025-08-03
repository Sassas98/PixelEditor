package org.example.view;

import javafx.scene.Parent;
import javafx.scene.control.Button;

public class ColorButton {

    public ColorButton(Parent root, String id, Runnable action){
        Button btn = (Button) root.lookup("#" +  id);
        btn.setOnAction(x -> action.run());
    }

}
