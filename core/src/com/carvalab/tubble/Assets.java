package com.carvalab.tubble;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
	public static TextureAtlas	mainAtlas;

	public static void load() {
		mainAtlas = new TextureAtlas(Gdx.files.internal("Tubble.pack"));
	}

	public static boolean loaded() {
		return mainAtlas != null;
	}

	public static void dispose() {
		if (mainAtlas != null) {
			mainAtlas.dispose();
		}
	}
}
