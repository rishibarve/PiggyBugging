package com.piggybugging.images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.piggybugging.Configuration;
import com.piggybugging.pojo.Coordinate;

public abstract class Image {

    private boolean isFlipped;
    private int height;
    private int width;
    private Coordinate coordinate;
    private boolean moveWithBackground;
    private Texture texture;


    public Image(int x, int y, boolean isFlipped, int height, int width, Texture texture){
        this.isFlipped = isFlipped;
        this.height = height;
        this.width =  width;
        this.coordinate = new Coordinate(x, y);
        moveWithBackground = false;
        this.texture = texture;

    }

    public void drawImage(SpriteBatch batch){
        if(coordinate.getX() + width > 0 &&
                coordinate.getX() < Gdx.graphics.getWidth()  &&
                coordinate.getY() + height > 0 &&
                coordinate.getY() < Gdx.graphics.getHeight()
        ){
            batch.draw(texture, coordinate.getX(), coordinate.getY(), width, height);
        }
        if(moveWithBackground){
            coordinate.setX(coordinate.getX() - Configuration.getInstance().getBackgroundSpeed());
        }
    }

    public Coordinate getCoordinate(){
        return this.coordinate;
    }
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public void setMoveWithBackground(boolean moveWithBackground){
        this.moveWithBackground = moveWithBackground;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
