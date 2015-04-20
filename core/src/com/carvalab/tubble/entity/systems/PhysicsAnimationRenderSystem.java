package com.carvalab.tubble.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.carvalab.tubble.entity.components.AnimationComponent;
import com.carvalab.tubble.entity.components.ComponentMappers;
import com.carvalab.tubble.entity.components.PhysicsComponent;

public class PhysicsAnimationRenderSystem extends IteratingSystem {
	private final SpriteBatch	batch;

	@SuppressWarnings("unchecked")
	public PhysicsAnimationRenderSystem(SpriteBatch bt) {
		super(Family.all(PhysicsComponent.class, AnimationComponent.class).get());

		batch = bt;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		PhysicsComponent pc = ComponentMappers.physics.get(entity);
		AnimationComponent ac = ComponentMappers.animation.get(entity);

		ac.update(deltaTime);

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
