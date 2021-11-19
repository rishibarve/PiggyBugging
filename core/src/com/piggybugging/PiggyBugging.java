	package com.piggybugging;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.piggybugging.animals.Elephant;
import com.piggybugging.background.Background;

	public class  PiggyBugging extends ApplicationAdapter {
	SpriteBatch batch;
	Background background;
	Elephant elephant;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Background();
		elephant = new Elephant(20, 20, false);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.53f,0.81f,0.92f, 1);
		background.drawBackground(batch);
		elephant.drawElephant(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
