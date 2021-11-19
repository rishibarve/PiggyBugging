package com.piggybugging.animals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Image {

    private boolean isFlipped;
    private int heightPercentage;
    private int widthPercentage;

    public Image(boolean isFlipped, int heightPercentage, int widthPercentage){
        this.isFlipped = isFlipped;

        this.heightPercentage = (int) ( Gdx.graphics.getHeight() * heightPercentage / 100 );
        this.widthPercentage =  (int) ( Gdx.graphics.getWidth() * widthPercentage / 100 );
    }

    public void drawImage(SpriteBatch batch, Texture texture, int x, int y){
        batch.begin();
        batch.draw(texture, x, y, widthPercentage, heightPercentage);
        batch.end();
    }

}
