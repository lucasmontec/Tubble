package com.carvalab.tubble.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.carvalab.tubble.entity.components.AnimationComponent;
import com.carvalab.tubble.entity.components.ComponentMappers;
import com.carvalab.tubble.entity.components.PhysicsComponent;

/**
 * This component renders a {@link Animation} using an {@link AnimationComponent} and a {@link PhysicsComponent}.
 * The animation is rendered at the physics body position. The animation component tint is applied to the rendering.
 * 
 * @author Lucas M Carvalhaes
 *
 */
public class PhysicsAnimationRenderSystem extends IteratingSystem {
	private final SpriteBatch	batch;

	public PhysicsAnimationRenderSystem(SpriteBatch bt) {
		super(Family.all(PhysicsComponent.class, AnimationComponent.class).get());

		batch = bt;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		PhysicsComponent pc = ComponentMappers.physics.get(entity);
		AnimationComponent ac = ComponentMappers.animation.get(entity);

		// Update the animation timer
		ac.update(deltaTime);

		// Draw the things
		batch.begin();
		batch.setColor(ac.getTint());
		batch.draw(
				ac.getKeyFrame(true),
				pc.getPosition().x,
				pc.getPosition().y,
				ac.getWidth(),
				ac.getHeight());
		batch.setColor(Color.WHITE);
		batch.end();
	}
}
