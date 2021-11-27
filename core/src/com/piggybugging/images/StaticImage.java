package com.piggybugging.images;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.piggybugging.Configuration;

public abstract class StaticImage extends Image{

    public StaticImage(int x, int y, int height, int width, boolean flip, String internalPath){
        super(x, y, flip, height, width, new Texture(internalPath));
    }

}
