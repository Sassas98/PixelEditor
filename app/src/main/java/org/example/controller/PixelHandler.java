package org.example.controller;

public interface PixelHandler {

    /**
     * Getter of the number of the pixel into the picture.
     * @return the number of the height and the wigth in pixel
     */
    public int getPixelNumber();

    /**
     * Setter of the color in specific positions
     * @param x first coordinate
     * @param y second coordinate
     * @param color color of the pixel
     * @return true if the setting works, false else
     */
    public boolean setPixelColor(int x, int y, String color);

    /**
     * Getter of the color into a specific position
     * @param x first coordinate
     * @param y second coordinate
     * @return the color into the position
     */
    public String getColorAt(int x, int y);

    /**
     * Getter of all the pixels
     * @return all the pixels in the right positions.
     */
    public String[][] getAllPixels();

    /**
     * Release all the resources
     */
    public void dispose();
}
