package org.example.controller;

import org.example.model.persistence.Disposable;

public interface PixelHandler<P> extends Disposable {
    
    /**
     * Getter of the min dimention of this getter
     * @return the min position
     */
    public P getPixelMinDimention();

    /**
     * Getter of the max dimention of this getter
     * @return the max position
     */
    public P getPixelMaxDimention();
}
