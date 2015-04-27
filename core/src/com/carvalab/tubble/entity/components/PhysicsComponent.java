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

/**
 * Adds a physics body using BOX2D to the owner entity.
 * To use this, add this to the entity as a component and then
 * call one of the create methods to make the body for that entity.
 * 
 * @author Lucas M Carvalhaes
 *
 */
public class PhysicsComponent extends Component {
	private Fixture	fixture;
	private Entity	owner;
	private float	scale;
	private float	width;
	private float	height;
	private Body	body;
	private World	world;

	/**
	 * Creates the body as a Shape physics object.
	 * 
	 * @param owner
	 *            The entity that has this component (will attatch to it using physics userdata)
	 * @param world
	 *            The physics world to create the physics body
	 * @param width
	 *            The desired physics width
	 * @param height
	 *            The desired physics height
	 * @param position
	 *            The position to instantiate the body in the world
	 * @param shape
	 *            The Shape to create
	 * @param fixtureDef
	 *            The fixture that define the shape physics properties
	 * @param dynamic
	 *            Set to true to create a dynamic object. False for static.
	 */
	public void create(Entity owner, World world, float width, float height, float scale, Vector2 position,
			Shape shape, FixtureDef fixtureDef, boolean dynamic) {
		// Define the body
		BodyDef bodyDef = new BodyDef();
		if (dynamic)
			bodyDef.type = BodyDef.BodyType.DynamicBody;
		else
			bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(
				Physics.toBox(position.x + width * scale / 2.0F),
				Physics.toBox(position.y + height * scale / 2.0F));

		// Make the body in the world
		Body body = world.createBody(bodyDef);

		// Attatch the shape to the fixture
		fixtureDef.shape = shape;

		// Create the fixture
		fixture = body.createFixture(fixtureDef);

		// Store the entity onwer
		setOwner(owner);
		body.setUserData(owner);

		// Store other data
		this.scale = scale;
		this.width = width;
		this.height = height;
		this.body = body;
		this.world = world;
	}

	/**
	 * Creates the body as a ChainShape physics line.
	 * 
	 * @param owner
	 *            The entity that has this component (will attatch to it using physics userdata)
	 * @param world
	 *            The physics world to create the physics body
	 * @param width
	 *            The desired physics width
	 * @param height
	 *            The desired physics height
	 * @param shape
	 *            The ChainShape to create
	 * @param fixtureDef
	 *            The fixture that define the shape physics properties
	 */
	public void createStaticLine(Entity owner, World world, float width, float height, ChainShape shape,
			FixtureDef fixtureDef) {
		// Define the body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(new Vector2(0.0F, 0.0F));

		// Make the body in the world
		Body body = world.createBody(bodyDef);

		// Attatch the shape to the fixture
		fixtureDef.shape = shape;

		// Create the fixture
		fixture = body.createFixture(fixtureDef);

		// Store the entity onwer
		setOwner(owner);
		if (owner != null) {
			body.setUserData(owner);
		}

		// Store other data
		this.width = width;
		this.height = height;
		this.body = body;
		this.world = world;
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

	public Fixture getFixture() {
		return fixture;
	}

	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}

	/**
	 * @return the body
	 */
	public Body getBody() {
		return body;
	}

	public World getWorld() {
		return world;
	}

}
