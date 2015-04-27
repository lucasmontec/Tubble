package com.carvalab.tubble.entity.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.carvalab.tubble.entity.components.ComponentMappers;
import com.carvalab.tubble.entity.components.LivingStateComponent;

public class LivingStateSystem extends IteratingSystem {

	private Engine	engine;

	public LivingStateSystem() {
		super(Family.all(LivingStateComponent.class).get());
	}

	@Override
	public void addedToEngine(Engine eng) {
		super.addedToEngine(eng);
		engine = eng;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		LivingStateComponent living = ComponentMappers.living.get(entity);

		switch (living.getState()) {
			case ALIVE:
				break;
			case DEAD:
				engine.removeEntity(entity);
				break;
			case DIYNG:
				break;
			default:
				break;
		}
	}

}
