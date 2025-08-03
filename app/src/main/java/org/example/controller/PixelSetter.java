package org.example.controller;

public interface PixelSetter<P,C> extends PixelHandler<P> {

    /**
     * Setter of the color in specific positions
     * @param p position
     * @param color color of the pixel
     * @return true if the setting works, false else
     */
    public boolean setPixelColor(P position, C color);

    /**
     * Reset all the pixels in the default color
     */
    public void resetAllPixels();

}
