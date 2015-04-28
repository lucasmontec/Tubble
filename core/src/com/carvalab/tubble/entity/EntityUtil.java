package com.carvalab.tubble.entity;

import com.badlogic.ashley.core.Entity;
import com.carvalab.tubble.entity.components.AnimationComponent;
import com.carvalab.tubble.entity.components.ComponentMappers;

public class EntityUtil {

	/**
	 * Gets the scaled width from the animation component of entity e.
	 * If e doesn't have a animation component, this returns 0.
	 * 
	 * @param e
	 *            The entity to extract width
	 * @return The entity width
	 */
	public static float getWidthFromAnimationComponent(Entity e) {
		AnimationComponent ac = ComponentMappers.animation.get(e);
		if (ac != null)
			return ac.getWidth();

		return 0f;
	}

	/**
	 * Gets the scaled height from the animation component of entity e.
	 * If e doesn't have a animation component, this returns 0.
	 * 
	 * @param e
	 *            The entity to extract Height
	 * @return The entity Height
	 */
	public static float getHeightFromAnimationComponent(Entity e) {
		AnimationComponent ac = ComponentMappers.animation.get(e);
		if (ac != null)
			return ac.getHeight();

		return 0f;
	}

}
