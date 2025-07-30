package org.example.controller;

import java.util.List;
import java.util.Optional;

import org.example.model.entity.Pixel;
import org.example.model.persistence.HibernateContext;

public class SimpleColors32PixelHandler implements PixelHandler {

    private HibernateContext<Pixel> db;

    public SimpleColors32PixelHandler(HibernateContext<Pixel> db){
        this.db = db;
    }

    @Override
    public int getPixelNumber() {
        return 32;
    }

    @Override
    public boolean setPixelColor(int x, int y, String color) {
        if(x < 0 || x > 31 || y < 0 || y > 31) return false;
        List<Pixel> list = db.load();
        Optional<Pixel> op = list.stream().filter(p -> p.getX() == x && p.getY() == y).findFirst();
        if(op.isEmpty()) {
            db.save(new Pixel(x, y, color));
        } else {
            Pixel pixel = op.get();
            pixel.setColor(color);
            db.save(pixel);
        }
        return true;
    }

    @Override
    public String getColorAt(int x, int y) {
        List<Pixel> list = db.load();
        Optional<Pixel> op = list.stream().filter(p -> p.getX() == x && p.getY() == y).findFirst();
        if(op.isEmpty()) return "white";
        else return op.get().getColor();
    }

    @Override
    public String[][] getAllPixels() {
        String[][] result = new String[32][32];
        List<Pixel> list = db.load();
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                int x = i, y = j;
                Optional<Pixel> op = list.stream().filter(p -> p.getX() == x && p.getY() == y).findFirst();
                if(op.isEmpty()) result[i][j] = "white";
                else result[i][j] = op.get().getColor();
            }
        }
        return result;
    }

    @Override
    public void dispose(){
        db.dispose();
    }

}
