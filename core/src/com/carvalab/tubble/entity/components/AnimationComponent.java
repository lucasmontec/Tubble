package com.carvalab.tubble.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.carvalab.tubble.Assets;

public class AnimationComponent extends Component {
	protected Color		color;
	protected Animation	animation;
	private float		animationStateTime;
	private float		width;
	private float		height;
	public float		scale	= 1.0f;

	public AnimationComponent(String animAsset, Color tint, float FrameDuration) {
		if (Assets.loaded()) {
			animation = new Animation(FrameDuration, Assets.mainAtlas.findRegions(animAsset));
		} else {
			throw new RuntimeException("load assets first!");
		}
		color = tint;
	}

	public void update(float dt) {
		animationStateTime += dt;
	}

	public TextureRegion getKeyFrame(boolean looping) {
		return animation.getKeyFrame(animationStateTime, looping);
	}

	public float getWidthUnscaled() {
		return animation.getKeyFrame(0f).getRegionWidth();
	}

	public float getHeightUnscaled() {
		return animation.getKeyFrame(0f).getRegionHeight();
	}

	public float getWidth() {
		if (width == 0f) {
			width = (animation.getKeyFrame(0f).getRegionWidth() * scale);
		}
		return width;
	}

	public float getHeight() {
		if (height == 0f) {
			height = (animation.getKeyFrame(0f).getRegionHeight() * scale);
		}
		return height;
	}

	public Color getTint() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public float getAnimationStateTime() {
		return animationStateTime;
	}

	public void setAnimationStateTime(float animationStateTime) {
		this.animationStateTime = animationStateTime;
	}

	public float getScale() {
		return scale;
	}
}
