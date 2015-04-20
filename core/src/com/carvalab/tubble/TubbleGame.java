package com.carvalab.tubble;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TubbleGame extends ApplicationAdapter {
	SpriteBatch	batch;
	Texture		img;

	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.2F, 0.2F, 0.7F, 1.0F);
		Gdx.gl.glClear(16384);
		batch.begin();
		batch.draw(img, 0.0F, 0.0F);
		batch.end();
	}
}
