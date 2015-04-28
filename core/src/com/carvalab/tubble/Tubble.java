package com.carvalab.tubble;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.carvalab.tubble.entity.entities.Ball;

public class Tubble {

	public static final float	ballScale	= 0.12f;
	public static final String	ballLoris	= "ball_loris";

	/**
	 * internal line spawner for the field spawner
	 */
	private static void spawnLine(Engine ashley, World world, float scrWidth, float scrHeight, float balls,
			float margin, float dstFromTop) {
		Vector2 pos = new Vector2();
		float centerspace = scrWidth - 2 * margin;
		float halfBallWidth = Assets.getAssetWidth(Tubble.ballLoris, Tubble.ballScale) * 0.5f;
		for (int x = 0; x < balls; x++) {
			pos.set(halfBallWidth + margin + (x * (centerspace / balls)), scrHeight - dstFromTop);
			Ball b = Ball.instantiateStatic(world, pos, Tubble.ballScale);
			ashley.addEntity(b);
		}
	}

	/**
	 * Spawns a ball field. Spawns the balls using the tubble {@link Ball} class factory. Uses a margin
	 * and some spacing. They are spawned static for the tubble map.
	 * 
	 * @param ashley
	 *            The ashley entity {@link Engine} to spawn the ball field to
	 * @param world
	 *            The Box2D {@link World} to add the static balls
	 * @param scrHeight
	 *            The screen height
	 * @param scrWidth
	 *            The screen width
	 * @param balls
	 *            How many balls per line?
	 * @param lines
	 *            How many lines?
	 * @param marginTop
	 *            The margin from the top of the screen
	 * @param marginLeft
	 *            The margin from the left (and right) of the screen
	 */
	public static void spawnField(Engine ashley, World world, float scrWidth, float scrHeight, float balls,
			float lines, float marginTop, float marginLeft) {
		float ballHeight = Assets.getAssetHeight(Tubble.ballLoris, Tubble.ballScale);
		for (int y = 0; y < lines; y++) {
			spawnLine(ashley, world, scrWidth, scrHeight, balls, marginLeft, marginTop + (y * ballHeight)
					+ ballHeight * 0.5f);
		}
	}
}
