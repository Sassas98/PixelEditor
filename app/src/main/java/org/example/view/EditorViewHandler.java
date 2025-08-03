package org.example.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import org.example.controller.SimpleColors2D32PixelGetter;
import org.example.controller.SimpleColors2D32PixelSetter;
import org.example.model.entity.Pixel;
import org.example.model.entity.PixelColor;
import org.example.model.persistence.HibernateContext;
import org.example.model.utility.Position2D;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EditorViewHandler {

    private PixelColor selectedColor;
    private SimpleColors2D32PixelGetter getter;
    private Parent root;
    
    public EditorViewHandler(Parent root, SimpleColors2D32PixelGetter getter){
        selectedColor = PixelColor.WHITE;
        this.root = root;
        this.getter = getter;
    }

    public void setUpColorButtons(){
        Arrays.asList(PixelColor.values()).stream().forEach(c -> {
            String id = c.toString().toLowerCase();
            new ColorButton(root, id, () -> selectedColor = c);
        });
    }

    public void setUpGrid(){
        GridPane grid = (GridPane) root.lookup("#grid");
        HashMap<Position2D, PixelColor> map = getter.getAllPixels();
        Position2D max = getter.getPixelMaxDimention();
        double h = grid.getPrefHeight() / (double)(max.x()+1);
        double w = grid.getPrefWidth() / (double)(max.y()+1);
        for(Entry<Position2D, PixelColor> pair : map.entrySet()){
            Color color = Color.web(pair.getValue().getRGBColorString());
            Rectangle rect = new Rectangle();
            rect.setWidth(w);
            rect.setHeight(h);
            rect.setFill(color);
            grid.add(rect, pair.getKey().x(), pair.getKey().y());
            rect.setOnMouseClicked(e -> {
                Color c = Color.web(selectedColor.getRGBColorString());
                rect.setFill(c);
                HibernateContext<Pixel> db = new HibernateContext<Pixel>(Pixel.class);
                SimpleColors2D32PixelSetter setter = new SimpleColors2D32PixelSetter(db);
                setter.setPixelColor(pair.getKey(), selectedColor);
                setter.dispose();
            });
        }
    }
}
