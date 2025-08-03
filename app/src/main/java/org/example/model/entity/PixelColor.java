package org.example.model.entity;

public enum PixelColor {
    WHITE,
    BLACK,
    YELLOW,
    RED,
    BLUE,
    GREEN,
    ORANGE,
    PURPLE,
    BROWN;

    public String getRGBColorString(){
        return switch (this) {
            case WHITE   -> "#FEF9F3";
            case YELLOW  -> "#DFFE00";
            case RED     -> "#990000";
            case ORANGE  -> "#FC6A03";
            case GREEN   -> "#0A6522";
            case BLUE    -> "#1134A6";
            case PURPLE  -> "#6F2DA8";
            case BROWN   -> "#492000";
            case BLACK   -> "#080403";
            default      -> throw new IllegalArgumentException("Unexpected value: " + this);
        };
    } 
}
