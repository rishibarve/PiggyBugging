package com.piggybugging.images;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.piggybugging.constants.SizeConstants;

public class Cactus extends StaticImage{

    public Cactus(int x, int y, boolean flip) {
        super(x, y, SizeConstants.CACTUS_HEIGHT, SizeConstants.CACTUS_WIDTH, flip, "cactus.png");
    }

    public void drawCactus(SpriteBatch batch){
        super.drawImage(batch);
    }

}
