package org.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.example.model.entity.Pixel;
import org.example.model.entity.PixelColor;
import org.example.model.persistence.HibernateContext;
import org.example.model.utility.Position2D;

public class SimpleColors2D32PixelGetter extends SimpleColors2D32PixelHandler implements PixelGetter<Position2D, PixelColor>{

    public SimpleColors2D32PixelGetter(HibernateContext<Pixel> db){
        super(db);
    }

    @Override
    public PixelColor getColorAt(Position2D position) {
        List<Pixel> list = db.load();
        Optional<Pixel> op = list.stream().filter(p -> p.getX() == position.x() && p.getY() == position.y()).findFirst();
        if(op.isEmpty()) return PixelColor.WHITE;
        else return op.get().getColor();
    }

    @Override
    public HashMap<Position2D, PixelColor> getAllPixels() {
        HashMap<Position2D, PixelColor> result = new HashMap<>();
        List<Pixel> list = db.load();
        for (int i = MIN; i < MAX+1; i++) {
            for (int j = MIN; j < MAX+1; j++) {
                handleXYCombination(result, list, i, j);
            }
        }
        return result;
    }

    /**
     * private method to insert into the map the specific color
     * @param result final hash map
     * @param list list of lixels
     * @param i x position
     * @param j y position
     */
    private void handleXYCombination(HashMap<Position2D, PixelColor> result, List<Pixel> list, int i, int j){
        Optional<Pixel> op = list.stream().filter(p -> p.getX() == i && p.getY() == j).findFirst();
        if(op.isEmpty()) 
            result.put(new Position2D(i, j), PixelColor.WHITE);
        else 
            result.put(new Position2D(i, j), op.get().getColor());
    }

}
