package org.example.model.utility;

public record Position2D (int x, int y){

    @Override
    public String toString() {
        return "Position2D [x: " + x() + ", y: " + y() + "]";
    }

    
}
