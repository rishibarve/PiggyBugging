package com.piggybugging.images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.piggybugging.Configuration;
import com.piggybugging.constants.SizeConstants;
import com.piggybugging.pojo.Coordinate;

public class Bug extends AnimatedImage{

    private CoordinateMigrator coordinateMigrator;
    private AnimatedImage controlledAnimal;
    private int controlledAnimalYDistance;
    private int horizontalMovementSpeed;
    private boolean isInAir;
    private int jetpack;
    BitmapFont font = new BitmapFont();

    public Bug(int x, int y) {
        super(x, y, SizeConstants.BUG_HEIGHT, SizeConstants.BUG_WIDTH, 10, false, new String[]{"bug.png"});
        this.controlledAnimalYDistance = (int) (SizeConstants.ELEPHANT_HEIGHT *0.6);
        this.controlledAnimal = new Elephant(x, y - controlledAnimalYDistance, false);
        this.horizontalMovementSpeed = 4;
        this.coordinateMigrator = new CoordinateMigrator();
        this.isInAir = false;
        this.jetpack = 1000;
    }

    public AnimatedImage getControlledAnimal() {
        return controlledAnimal;
    }

    public AnimatedImage jump(){
        AnimatedImage output = controlledAnimal;
        controlledAnimal = null;
        isInAir = true;
        return output;
    }

    public boolean moveUp(int distance){
        return coordinateMigrator.moveUp(distance);
    }


    public boolean moveDown(int distance){
        return coordinateMigrator.moveDown(distance);
    }

    public boolean moveForward(){
        super.setCoordinate(new Coordinate(super.getCoordinate().getX() + horizontalMovementSpeed, super.getCoordinate().getY()));
        if (controlledAnimal != null){
            controlledAnimal.setCoordinate(new Coordinate(controlledAnimal.getCoordinate().getX() + horizontalMovementSpeed, controlledAnimal.getCoordinate().getY()));
        }
        return true;
    }

    public boolean moveBackward(){
        super.setCoordinate(new Coordinate(super.getCoordinate().getX() - horizontalMovementSpeed, super.getCoordinate().getY()));
        if(controlledAnimal != null){
            controlledAnimal.setCoordinate(new Coordinate(controlledAnimal.getCoordinate().getX() - horizontalMovementSpeed, controlledAnimal.getCoordinate().getY()));
        }
        return true;
    }

    @Override
    public void drawAnimatedObject(SpriteBatch batch){
        if(controlledAnimal != null){
            controlledAnimal.drawAnimatedObject(batch);
        }

        super.drawAnimatedObject(batch);
        super.setCoordinate(coordinateMigrator.migrate(super.getCoordinate()));
        if (controlledAnimal != null) {
            controlledAnimal.setCoordinate(new Coordinate(super.getCoordinate().getX(), super.getCoordinate().getY() - controlledAnimalYDistance));
        }
        if(isInAir && jetpack > 0) jetpack--;
        if(!isInAir) jetpack++;
        if(jetpack <= 0 && !Configuration.getInstance().isDead()){
            Configuration.getInstance().setDead(true);
        }
        font.draw(batch, String.valueOf(jetpack), (int) Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight() - 120 );
    }

    public boolean isInAir() {
        return isInAir;
    }

    public boolean controlAnimal(Elephant animalImage){
        this.controlledAnimal = animalImage;
        this.isInAir = false;
        this.setCoordinate(new Coordinate(this.controlledAnimal.getCoordinate().getX(), this.controlledAnimal.getCoordinate().getY() + controlledAnimalYDistance));
        return false;
    }

}
