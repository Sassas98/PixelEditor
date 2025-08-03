package org.example.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import org.example.controller.PixelGetter;
import org.example.model.entity.PixelColor;
import org.example.model.utility.Position2D;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EditorViewHandler {

    private PixelColor selectedColor;
    private PixelGetter<Position2D, PixelColor> getter;
    private Parent root;
    private Rectangle hoverRect;
    private ColorSetterAction action;
    private double h, w;
    
    public EditorViewHandler(Parent root, PixelGetter<Position2D, PixelColor> getter, ColorSetterAction action){
        selectedColor = PixelColor.WHITE;
        this.root = root;
        this.getter = getter;
        this.action = action;
    }

    public void setUpColorButtons(){
        Arrays.asList(PixelColor.values()).stream().forEach(c -> {
            String id = c.toString().toLowerCase();
            new ColorButton(root, id, () -> selectedColor = c, c.getRGBColorString());
        });
    }

    public void setUpGrid(){
        GridPane grid = (GridPane) root.lookup("#grid");
        HashMap<Position2D, PixelColor> map = getter.getAllPixels();
        Position2D max = getter.getPixelMaxDimention();
        h = (grid.getPrefHeight() / (double)(max.x()+1)) - 2.0;
        w = (grid.getPrefWidth() / (double)(max.y()+1)) - 3.0;
        for(Entry<Position2D, PixelColor> pair : map.entrySet()){
            Rectangle rect = setUpRectangle(pair.getKey(), pair.getValue());
            grid.add(rect, pair.getKey().x(), pair.getKey().y());
        }
    }

    private Rectangle setUpRectangle(Position2D position, PixelColor pc){
        Color color = Color.web(pc.getRGBColorString());
        Rectangle rect = new Rectangle();
        rect.setWidth(w);
        rect.setHeight(h);
        rect.setFill(color);
        rect.setStroke(Color.BLACK);
        return setUpRectangle(rect, position);
    }

    private Rectangle setUpRectangle(Rectangle rect, Position2D position){
        rect.setOnMouseEntered(e -> {
            if(hoverRect != null)
                hoverRect.setStroke(Color.BLACK);
            rect.setStroke(Color.RED);
            hoverRect = rect;
        });
        rect.setOnMouseClicked(e -> {
            Color c = Color.web(selectedColor.getRGBColorString());
            rect.setFill(c);
            action.set(position, selectedColor);
        });
        return rect;
    }
}
