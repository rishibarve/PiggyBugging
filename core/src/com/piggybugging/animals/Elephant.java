package com.piggybugging.animals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Elephant extends AnimatedImage{

    String[] internalPaths = {"elephant.png", "elephant1.png"};
    private int x;
    private int y;

    public Elephant(int x, int y, boolean flip){
        super(12, 12, 10, flip, new String[]{"elephant.png", "elephant1.png"});
        this.x = x;
        this.y = y;
    }

    public void drawElephant(SpriteBatch batch){
        super.drawAnimatedObject(batch, x, y);
    }

}
