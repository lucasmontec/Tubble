package com.carvalab.tubble.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.carvalab.tubble.entity.components.AnimationComponent;
import com.carvalab.tubble.entity.components.ComponentMappers;
import com.carvalab.tubble.entity.components.PositionComponent;

/**
 * Component for rendering animations that use a position component.
 * No physics.
 * 
 * @author Lucas M Carvalhaes
 *
 */
public class AnimationRenderSystem extends IteratingSystem {
	private final SpriteBatch	batch;

	public AnimationRenderSystem(SpriteBatch bt) {
		super(Family.all(AnimationComponent.class, PositionComponent.class).get());

		batch = bt;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		AnimationComponent ac = ComponentMappers.animation.get(entity);
		PositionComponent pc = ComponentMappers.position.get(entity);

		// Update the animation timer
		ac.update(deltaTime);

		// Draw the things
		batch.begin();
		batch.setColor(ac.getTint());
		batch.draw(
				ac.getKeyFrame(true),
				pc.X(),
				pc.Y(),
				0f,
				0f,
				ac.getWidth(),
				ac.getHeight(),
				1f,
				1f,
				ac.getAngle());
		batch.setColor(Color.WHITE);
		batch.end();
	}
}
