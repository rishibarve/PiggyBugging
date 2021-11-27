package com.piggybugging.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.piggybugging.Configuration;

public class Background {

    private Texture texture;
    private double width;
    private double height;
    private int x1;
    private int x2;

    public Background(){
        texture = new Texture("desert_background.png");
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight()*1.4;
        x1 = 0;
        x2 = (int) width;
    }

    public void drawBackground(SpriteBatch batch){
        batch.draw(texture, x1, 0, (int) width, (int) height);
        batch.draw(texture, x2, 0, (int) width, (int) height);
        if(x1 > width*-1){
            x1-=Configuration.getInstance().getBackgroundSpeed();
            x2-=Configuration.getInstance().getBackgroundSpeed();
        }else{
            x1 = 0;
            x2 = (int) width;
        }
    }

    public void dispose(){
        texture.dispose();
    }

}
