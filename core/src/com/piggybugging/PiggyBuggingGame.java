package com.piggybugging;

import com.badlogic.gdx.Game;


public class PiggyBuggingGame extends Game {
	GameScreen gameScreen;

	@Override
	public void dispose() {
		gameScreen.dispose();
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		gameScreen.resize(width, height);
	}

	@Override
	public void create() {

		gameScreen = new GameScreen();
		setScreen(gameScreen);

	}
}
