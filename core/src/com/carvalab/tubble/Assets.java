package com.carvalab.tubble;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static TextureAtlas	mainAtlas;

	// Tiny cache
	private static Texture		lastTex;
	private static String		lastFile;

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

	/**
	 * This is supposed to be used only when loading things. NOT A RUNTIME.
	 * This allocates a texture.
	 * 
	 * @param asset
	 *            The asset name
	 * @param scale
	 *            Scale the width by a factor
	 * @return The scaled width
	 */
	public static float getAssetWidth(String asset, float scale) {
		if (!loaded()) {
			throw new RuntimeException("Asset unavaliable. Load assets first: Call Assets.load()");
		}
		TextureRegion reg = mainAtlas.findRegion(asset);
		if (reg == null)
			return 0;
		return reg.getRegionWidth() * scale;
	}

	/**
	 * This is supposed to be used only when loading things. NOT A RUNTIME.
	 * This allocates a texture.
	 * 
	 * @param asset
	 *            The asset name
	 * @param scale
	 *            Scale the width by a factor
	 * @return The scaled height
	 */
	public static float getAssetHeight(String asset, float scale) {
		if (!loaded()) {
			throw new RuntimeException("Asset unavaliable. Load assets first: Call Assets.load()");
		}
		TextureRegion reg = mainAtlas.findRegion(asset);
		if (reg == null)
			return 0;
		return reg.getRegionHeight() * scale;
	}

	/**
	 * This is supposed to be used only when loading things. NOT A RUNTIME.
	 * This allocates a texture.
	 * 
	 * @param file
	 *            The asset file
	 * @return The scaled width
	 */
	public static float getAssetWidth(String file) {
		return getAssetWidth(file, 1f);
	}

	/**
	 * This is supposed to be used only when loading things. NOT A RUNTIME.
	 * This allocates a texture.
	 * 
	 * @param file
	 *            The asset file
	 * @return The scaled height
	 */
	public static float getAssetHeight(String file) {
		return getAssetHeight(file, 1f);
	}

	/**
	 * This is supposed to be used only when loading things. NOT A RUNTIME.
	 * This allocates a texture.
	 * 
	 * @param file
	 *            The asset file
	 * @param scale
	 *            Scale the width by a factor
	 * @return The scaled width
	 */
	public static float getFileAssetWidth(String file, float scale) {
		if (file != lastFile) {
			lastFile = file;
			lastTex = new Texture(Gdx.files.internal(file));
		}
		return lastTex.getWidth() * scale;
	}

	/**
	 * This is supposed to be used only when loading things. NOT A RUNTIME.
	 * This allocates a texture.
	 * 
	 * @param file
	 *            The asset file
	 * @param scale
	 *            Scale the width by a factor
	 * @return The scaled height
	 */
	public static float getFileAssetHeight(String file, float scale) {
		if (file != lastFile) {
			lastFile = file;
			lastTex = new Texture(Gdx.files.internal(file));
		}
		return lastTex.getHeight() * scale;
	}

	/**
	 * This is supposed to be used only when loading things. NOT A RUNTIME.
	 * This allocates a texture.
	 * 
	 * @param file
	 *            The asset file
	 * @return The scaled width
	 */
	public static float getFileAssetWidth(String file) {
		return getFileAssetWidth(file, 1f);
	}

	/**
	 * This is supposed to be used only when loading things. NOT A RUNTIME.
	 * This allocates a texture.
	 * 
	 * @param file
	 *            The asset file
	 * @return The scaled height
	 */
	public static float getFileAssetHeight(String file) {
		return getFileAssetHeight(file, 1f);
	}
}
