package com.carvalab.tubble.entity.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.carvalab.tubble.Physics;
import com.carvalab.tubble.entity.components.PhysicsComponent;

public class Wall extends Entity {
	private final PhysicsComponent	pc;
	private final ChainShape		wallShape	= new ChainShape();
	private final FixtureDef		wallFixture	= new FixtureDef();

	public Wall() {
		pc = new PhysicsComponent();
		add(pc);
	}

	public static Wall instantiate(World w, Vector2 start, Vector2 end) {
		Wall b = new Wall();

		b.wallShape.createChain(new Vector2[]
				{ Physics.toBox(start), Physics.toBox(end) });

		b.wallFixture.friction = 0.5F;
		b.wallFixture.restitution = 0.0F;

		b.pc.createStaticLine(b, w, end.x - start.x, end.y - start.y, b.wallShape, b.wallFixture);
		return b;
	}

	public void dispose() {
		if (wallShape != null) {
			wallShape.dispose();
		}
	}
}
