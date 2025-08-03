package org.example.controller;

import java.util.List;
import java.util.Optional;

import org.example.model.entity.Pixel;
import org.example.model.entity.PixelColor;
import org.example.model.persistence.HibernateContext;
import org.example.model.utility.Position2D;

public class SimpleColors2D32PixelSetter extends SimpleColors2D32PixelHandler implements PixelSetter<Position2D, PixelColor>{

    public SimpleColors2D32PixelSetter(HibernateContext<Pixel> db){
        super(db);
    }

    @Override
    public boolean setPixelColor(Position2D position, PixelColor color) {
        System.out.println(position);
        if(isCoordinateWrong(position.x()) || isCoordinateWrong(position.y())) 
            return false;
        Pixel pixel = getPixel(position);
        if(pixel == null) {
            db.save(new Pixel(position.x(), position.y(), color));
        } else {
            pixel.setColor(color);
            db.update(pixel);
        }
        return true;
    }

    /**
     * Private method for check if a coordinate is wrong
     * @param num number to check
     * @return true if the number is wrong
     */
    private boolean isCoordinateWrong(int num){
        return num < MIN || num > MAX;
    }

    /**
     * Getter of a pixel into a position
     * @param position position where find
     * @return the pixel into the specific position
     */
    private Pixel getPixel(Position2D position){
        List<Pixel> list = db.load();
        Optional<Pixel> op = list.stream().filter(p -> p.getX() == position.x() && p.getY() == position.y()).findFirst();
        return op.isPresent() ? op.get() : null;
    }

    @Override
    public void resetAllPixels() {
        db.load().stream().filter(x -> x.getColor() != PixelColor.WHITE).forEach(x -> {
            x.setColor(PixelColor.WHITE);
            db.update(x);
        });
    }

}
