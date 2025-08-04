package org.example.controller;

import java.util.Stack;

import org.example.model.entity.Pixel;
import org.example.model.entity.PixelColor;
import org.example.model.persistence.Context;
import org.example.model.utility.Position2D;

public class SimpleColors2D32PixelFillSetter extends SimpleColors2D32PixelSetter implements PixelFillSetter<Position2D, PixelColor> {

    public SimpleColors2D32PixelFillSetter(Context<Pixel> db) {
        super(db);
    }

    @Override
    public boolean fillPixelColor(Position2D position, PixelColor color) {
        if(isCoordinateWrong(position.x()) || isCoordinateWrong(position.y())) 
            return false;
        Stack<Position2D> stack = new Stack<>();
        stack.add(position);
        Pixel p = getPixel(position);
        PixelColor init = p == null ? PixelColor.WHITE : p.getColor();
        while(!stack.isEmpty()){
            Position2D actual = stack.pop();
            handlePosition(stack, actual, init, color);
        }
        return true;
    }

    private void handlePosition(Stack<Position2D> stack, Position2D actual, PixelColor init, PixelColor color){
        if(isCoordinateWrong(actual.x()) || isCoordinateWrong(actual.y())) 
            return;
        Pixel pa = getPixel(actual);
        if((pa == null && init == PixelColor.WHITE) || pa.getColor() == init){
            addAllPositionToStack(stack, actual);
            setPixelColor(actual, color);
        }
    }

    private void addAllPositionToStack(Stack<Position2D> stack, Position2D actual){
        for (int i = -1; i < 2; i+=2) {
            for (int j = -1; j < 2; j+=2) {
                stack.add(new Position2D(actual.x()+i, actual.y()+j));
            }
        }
    }

}
