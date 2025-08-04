package org.example.controller;

public interface PixelFillSetter<P,C> extends PixelSetter<P,C>{

    public boolean fillPixelColor(P position, C color);

}
