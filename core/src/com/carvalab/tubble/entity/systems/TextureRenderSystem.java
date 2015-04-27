package com.carvalab.tubble.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.carvalab.tubble.entity.components.ComponentMappers;
import com.carvalab.tubble.entity.components.PositionComponent;
import com.carvalab.tubble.entity.components.TextureComponent;

/**
 * Component for rendering animations that use a position component.
 * No physics.
 * 
 * @author Lucas M Carvalhaes
 *
 */
public class TextureRenderSystem extends IteratingSystem {
	private final SpriteBatch	batch;

	public TextureRenderSystem(SpriteBatch bt) {
		super(Family.all(PositionComponent.class).one(TextureComponent.class).get());

		batch = bt;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		TextureComponent tc = ComponentMappers.texture.get(entity);
		PositionComponent pc = ComponentMappers.position.get(entity);

		// Draw the things
		batch.begin();
		batch.setColor(tc.getTint());
		if (!tc.centered)
			batch.draw(
					tc.textureAsRegion(),
					pc.X(),
					pc.Y(),
					tc.getWidth() / 2,
					tc.getHeight() / 2,
					tc.getWidth(),
					tc.getHeight(),
					1f,
					1f,
					tc.getAngle());
		else
			batch.draw(
					tc.textureAsRegion(),
					pc.X() - tc.getWidth() / 2,
					pc.Y() - tc.getHeight() / 2,
					tc.getWidth() / 2,
					tc.getHeight() / 2,
					tc.getWidth(),
					tc.getHeight(),
					1f,
					1f,
					tc.getAngle());
		batch.setColor(Color.WHITE);
		batch.end();
	}
}
