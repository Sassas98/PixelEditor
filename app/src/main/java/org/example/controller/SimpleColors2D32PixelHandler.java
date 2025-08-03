package org.example.controller;

import org.example.model.entity.Pixel;
import org.example.model.persistence.HibernateContext;
import org.example.model.utility.Position2D;

public abstract class SimpleColors2D32PixelHandler implements PixelHandler<Position2D> {

    protected static final int MIN = 0, MAX = 31;

    protected HibernateContext<Pixel> db;

    public SimpleColors2D32PixelHandler(HibernateContext<Pixel> db){
        this.db = db;
    }

    @Override
    public void dispose() {
        db.dispose();
    }

    @Override
    public Position2D getPixelMinDimention() {
        return new Position2D(MIN, MIN);
    }

    @Override
    public Position2D getPixelMaxDimention() {
        return new Position2D(MAX, MAX);
    }
}
