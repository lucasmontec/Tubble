package com.carvalab.tubble.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.carvalab.tubble.Physics;

public class PhysicsComponent extends Component {
	private Fixture	fixture;
	private Entity	owner;
	private float	scale;
	private float	width;
	private float	height;
	private Body	body;

	public Fixture getFixture() {
		return fixture;
	}

	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}

	public void createDynamic(Entity owner, World world, float width, float height, float scale,
			Vector2 position, Shape shape, FixtureDef fixtureDef) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(
				Physics.toBox(position.x + width * scale / 2.0F),
				Physics.toBox(position.y + height * scale / 2.0F));

		Body body = world.createBody(bodyDef);

		fixtureDef.shape = shape;

		fixture = body.createFixture(fixtureDef);

		setOwner(owner);

		body.setUserData(owner);

		this.scale = scale;
		this.width = width;
		this.height = height;
		this.body = body;
	}

	public void createStaticLine(Entity owner, World world, float width, float height, ChainShape shape,
			FixtureDef fixtureDef) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;

		bodyDef.position.set(new Vector2(0.0F, 0.0F));

		Body body = world.createBody(bodyDef);

		fixtureDef.shape = shape;

		fixture = body.createFixture(fixtureDef);

		setOwner(owner);
		if (owner != null) {
			body.setUserData(owner);
		}
		this.width = width;
		this.height = height;
		this.body = body;
	}

	public Vector2 getPosition() {
		return Physics.fromBox(body.getPosition()).sub(width / 2.0F, height / 2.0F);
	}

	public Entity getOwner() {
		return owner;
	}

	public void setOwner(Entity owner) {
		this.owner = owner;
	}

	public float getScale() {
		return scale;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
}
