package com.carvalab.tubble;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Physics {
	public static final float			TO_BOX_RATIO	= 0.01F;
	public static final float			FROM_BOX_RATIO	= 100.0F;
	private static Box2DDebugRenderer	debugRenderer;

	public static float toBox(float pixels) {
		return pixels * 0.01F;
	}

	public static Vector2 toBox(Vector2 pixels) {
		return pixels.cpy().scl(0.01F);
	}

	public static float fromBox(float box) {
		return box * 100.0F;
	}

	public static Vector2 fromBox(Vector2 box) {
		return box.cpy().scl(100.0F);
	}

	public static void debug(World w, Camera cam) {
		if (debugRenderer == null) {
			debugRenderer = new Box2DDebugRenderer();
		}
		debugRenderer.render(w, cam.combined.cpy().scl(100.0F));
	}
}
