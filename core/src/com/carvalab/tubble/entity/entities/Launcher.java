package com.carvalab.tubble.entity.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Launcher {

	private final TextureRegion	underTexture;
	private final TextureRegion	overTexture;
	private final Vector2		position;
	private final float			scale			= 0.4f;
	private float				angle			= 0f;
	private float				chargePercent	= 0.8f;

	public Launcher(Vector2 location) {
		underTexture = new TextureRegion(new Texture(Gdx.files.internal("launcher.png")));

		position = location;
		// setChargePercent(0.8f);
		overTexture = new TextureRegion(new Texture(Gdx.files.internal("launcher_full_flip.png")));
		overTexture.flip(false, true);
	}

	public void lookTo(Vector2 pos) {
		Vector2 target = new Vector2(pos.x, 600).sub(0, pos.y);
		angle = target.sub(position.cpy()).angle() - 90;
	}

	public void draw(SpriteBatch batch) {
		// Draw default under texture
		batch.draw(
				underTexture,
				position.x - underTexture.getRegionWidth() * scale / 2,
				position.y - underTexture.getRegionHeight() * scale / 2,
				underTexture.getRegionWidth() * scale / 2,
				underTexture.getRegionHeight() * scale / 2,
				underTexture.getRegionWidth() * scale,
				underTexture.getRegionHeight() * scale,
				1f,
				1f,
				angle);
		// Draw the top charging texture
		batch.draw(
				overTexture,
				position.x - underTexture.getRegionWidth() * scale / 2,
				position.y - underTexture.getRegionHeight() * scale / 2,
				overTexture.getRegionWidth() * scale / 2,
				(underTexture.getRegionHeight() * scale) / 2f,
				overTexture.getRegionWidth() * scale,
				overTexture.getRegionHeight() * scale,
				1f,
				1f,
				angle);

	}

	public Vector2 getPosition() {
		return position;
	}

	public TextureRegion getMainTex() {
		return underTexture;
	}

	public float getChargePercent() {
		return chargePercent;
	}

	/**
	 * OTH = Over Texture height
	 */
	private float calculateOTHPerCharge() {
		return (1f - chargePercent) * overTexture.getTexture().getHeight();
	}

	public void setChargePercent(float chargePercent) {
		this.chargePercent = chargePercent;

		overTexture.setRegionHeight(overTexture.getTexture().getHeight());
		overTexture.setRegionHeight(overTexture.getRegionHeight() - (int) (calculateOTHPerCharge()));
	}

}
