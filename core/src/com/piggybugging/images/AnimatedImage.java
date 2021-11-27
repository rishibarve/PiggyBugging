package com.piggybugging.images;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.piggybugging.Configuration;

import java.util.ArrayList;

public abstract class AnimatedImage extends Image{

    private ArrayList<Texture> textures;
    private int animationSpeed;
    private int counter;
    private int textureIndex;

    public AnimatedImage(int x, int y, int height, int width, int animationSpeed, boolean flip, String[] internalPaths){

        super(x, y, flip, height, width, null);

        textures = new ArrayList<>(internalPaths.length);

        for (String internalPath : internalPaths) {
            this.textures.add(new Texture(internalPath));
        }
        this.animationSpeed = animationSpeed;
        this.textureIndex = 0;
        counter = 0;

        super.setTexture(textures.get(textureIndex));
    }

    private Texture getAnimatedTexture(){

        Texture output;

        if (counter / animationSpeed >= 1){
            textureIndex++;
            counter = 0;
        }
        if (textureIndex >= textures.size()){
            textureIndex = 0;
        }

        counter++;

        output = textures.get(textureIndex);
        return output;
    }

    public void drawAnimatedObject(SpriteBatch batch){
        super.setTexture(getAnimatedTexture());
        super.drawImage(batch);

    }
}
