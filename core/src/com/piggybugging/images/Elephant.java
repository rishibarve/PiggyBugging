package com.piggybugging.images;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.piggybugging.constants.SizeConstants;

public class Elephant extends AnimatedImage{

    public Elephant(int x, int y, boolean flip){
        super(x, y, SizeConstants.ELEPHANT_HEIGHT, SizeConstants.ELEPHANT_WIDTH, 10, flip, new String[]{"elephant.png", "elephant1.png"});
    }


}
