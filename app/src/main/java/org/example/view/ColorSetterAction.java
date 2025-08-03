package org.example.view;

import org.example.model.entity.PixelColor;
import org.example.model.utility.Position2D;

public interface ColorSetterAction {
    /**
     * action that set a color into a position
     * @param p
     * @param c
     */
    public void set(Position2D p, PixelColor c);
}
