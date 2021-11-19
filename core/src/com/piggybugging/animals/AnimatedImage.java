package com.piggybugging.animals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public abstract class AnimatedImage extends Image{

    private ArrayList<Texture> textures;
    private int animationSpeed;
    private int counter;
    private int textureIndex;

    public AnimatedImage(int heightPercentage, int widthPercentage, int animationSpeed, boolean flip, String[] internalPaths){

        super(flip, heightPercentage, widthPercentage);

        textures = new ArrayList<>(internalPaths.length);

        for (String internalPath : internalPaths) {
            this.textures.add(new Texture(internalPath));
        }
        this.animationSpeed = animationSpeed;
        this.textureIndex = 0;
        counter = 0;
    }

    private Texture getTexture(){

        Texture output;

        if (counter / animationSpeed >= 1){
            textureIndex++;
        }
        if (textureIndex >= textures.size()){
            textureIndex = 0;
        }

        counter++;
        if(counter > 2*animationSpeed){
            counter = 0;
        }

        output = textures.get(textureIndex);
        return output;
    }

    public void drawAnimatedObject(SpriteBatch batch, int x, int y){
        Texture texture = getTexture();
        super.drawImage(batch, texture, x, y);
    }
}
