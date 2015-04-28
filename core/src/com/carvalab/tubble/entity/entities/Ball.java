package com.carvalab.tubble.entity.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.carvalab.tubble.Physics;
import com.carvalab.tubble.Tubble;
import com.carvalab.tubble.entity.components.AnimationComponent;
import com.carvalab.tubble.entity.components.LivingStateComponent;
import com.carvalab.tubble.entity.components.PhysicsComponent;

public class Ball extends Entity {
	private final PhysicsComponent		pc;
	private final AnimationComponent	ac;
	private final LivingStateComponent	lc;
	private final CircleShape			ballShape	= new CircleShape();
	private final FixtureDef			ballFixture	= new FixtureDef();

	private Ball() {
		pc = new PhysicsComponent();
		ac = new AnimationComponent(Tubble.ballLoris, Tubble.ballScale);
		lc = new LivingStateComponent();
		add(pc);
		add(ac);
		add(lc);
	}

	private static Ball instantiate(World w, Vector2 pos, float scale, boolean dynamic) {
		Ball b = new Ball();
		// Set the ball scale
		b.ac.scale = scale;
		// Create the ball shape correctly - radius is half the width
		b.ballShape.setRadius(Physics.toBox(b.ac.getWidth()) * 0.5f);
		// Make the fixture
		b.ballFixture.density = 1.0f;
		b.ballFixture.friction = 0.1f;
		b.ballFixture.restitution = 0.8f;
		Physics.configureFixtureAsPhysicsObject(b.ballFixture);
		// Create the body
		b.pc.create(b, w, b.ac.getWidth(), b.ac.getHeight(), scale, pos, b.ballShape, b.ballFixture, dynamic);

		return b;
	}

	public static Ball instantiateStatic(World w, Vector2 pos, float scale) {
		return instantiate(w, pos, scale, false);
	}

	public static Ball instantiate(World w, Vector2 pos, float scale) {
		return instantiate(w, pos, scale, true);
	}

	public void dispose() {
		if (ballShape != null) {
			ballShape.dispose();
		}
	}
}
