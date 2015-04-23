package com.carvalab.tubble.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A component that holds a texture to render for the entity.
 * 
 * @author Lucas M Carvalhaes
 *
 */
public class TextureComponent extends Component {

	private Texture	texture;
	private float		width;
	private float		height;
	private float	angle	= 0f;
	public float		scale	= 1.0f;
	protected Color			tint	= Color.WHITE;
	private TextureRegion	asRegion;
	public boolean			centered	= true;

	public TextureComponent(Texture t) {
		texture = t;
	}

	public TextureRegion textureAsRegion() {
		if (asRegion == null)
			asRegion = new TextureRegion(texture);
		return asRegion;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
		asRegion = new TextureRegion(texture);
	}

	public float getWidthUnscaled() {
		return texture.getWidth();
	}

	public float getHeightUnscaled() {
		return texture.getHeight();
	}

	public float getWidth() {
		if (width == 0f) {
			width = (texture.getWidth() * scale);
		}
		return width;
	}

	public float getHeight() {
		if (height == 0f) {
			height = (texture.getHeight() * scale);
		}
		return height;
	}

	public void setTint(Color t) {
		tint = t;
	}

	public Color getTint() {
		return tint;
	}

	public float getScale() {
		return scale;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

}
