package org.example.controller;

import java.util.HashMap;

public interface PixelGetter<P,C> extends PixelHandler<P> {
    /**
     * Getter of the color at the position
     * @param position position of the color
     * @return color into the position
     */
    public C getColorAt(P position);

    /**
     * getter of the pair list, with each color has his position
     * @return the colors into each position
     */
    public HashMap<P, C> getAllPixels();
}
