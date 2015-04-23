package com.carvalab.tubble;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * A physics utility class that helps handeling Box2d and LibGDX together.
 * 
 * @author Lucas M Carvalhaes
 *
 */
public class Physics {
	public static final float			FROM_BOX_RATIO		= 100.0f;
	public static final float			TO_BOX_RATIO		= 0.01f;
	public static final short			WORLD_ENTITY_MASK	= 0x1;
	public static final short			PHYSICS_ENTITY_MASK	= 0x1 << 1;
	private static Box2DDebugRenderer	debugRenderer;

	/**
	 * Gives a model value to box2d world. This scales the float down using the
	 * physics factor (default 0.01x). This is because box2d uses meters
	 * as the default unit and LIBGDX and OPENGL both uses pixels.
	 * This scales the model float by TO_BOX_RATIO.
	 * 
	 * @param pixels
	 *            The pixel float with pixel coordinates
	 * @return The box2d float with meter coordinates
	 */
	public static float toBox(float pixels) {
		return pixels * TO_BOX_RATIO;
	}

	/**
	 * Gives a model value to box2d world. This scales the vector down using the
	 * physics factor (default 0.01x). This is because box2d uses meters
	 * as the default unit and LIBGDX and OPENGL both uses pixels.
	 * This scales the model vector by TO_BOX_RATIO.
	 * 
	 * @param pixels
	 *            The pixel vector with pixel coordinates
	 * @return The box2d vector with meter coordinates
	 */
	public static Vector2 toBox(Vector2 pixels) {
		return pixels.cpy().scl(TO_BOX_RATIO);
	}

	/**
	 * Retrieves a float from box2d. This scales the float using the
	 * physics factor (default 100x). This is because box2d uses meters
	 * as the default unit and LIBGDX and OPENGL both uses pixels.
	 * This scales the box2d float by FROM_BOX_RATIO.
	 * 
	 * @param box
	 *            The float provided by the box2d world
	 * @return The float of that value in pixel units
	 */
	public static float fromBox(float box) {
		return box * FROM_BOX_RATIO;
	}

	/**
	 * Retrieves a vector from box2d. This scales the vetor using the
	 * physics factor (default 100x). This is because box2d uses meters
	 * as the default unit and LIBGDX and OPENGL both uses pixels.
	 * This scales the box2d vector by FROM_BOX_RATIO.
	 * 
	 * @param box
	 *            The vector provided by the box2d body
	 * @return The vector of that position in pixel units
	 */
	public static Vector2 fromBox(Vector2 box) {
		return box.cpy().scl(FROM_BOX_RATIO);
	}

	/**
	 * Enable debug drawing of physics
	 * 
	 * @param w
	 *            The world to draw.
	 * @param cam
	 *            The camera to draw to
	 */
	public static void debug(World w, Camera cam) {
		if (debugRenderer == null) {
			debugRenderer = new Box2DDebugRenderer();
		}
		debugRenderer.render(w, cam.combined.cpy().scl(100.0F));
	}

	/**
	 * Configures the fixture f to be a world object that only collides with physics entitites
	 * 
	 * @param f
	 */
	public static void configureFixtureAsWorld(FixtureDef f) {
		f.filter.categoryBits = WORLD_ENTITY_MASK;
		f.filter.maskBits = PHYSICS_ENTITY_MASK;
	}

	/**
	 * Configures the fixture f to be a physics entity object that collides with physics entitites and the world
	 * 
	 * @param f
	 */
	public static void configureFixtureAsPhysicsObject(FixtureDef f) {
		f.filter.categoryBits = PHYSICS_ENTITY_MASK;
		f.filter.maskBits = PHYSICS_ENTITY_MASK | WORLD_ENTITY_MASK;
	}
}
