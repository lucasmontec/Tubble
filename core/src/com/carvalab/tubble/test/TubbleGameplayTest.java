package com.carvalab.tubble.test;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.carvalab.tubble.Assets;
import com.carvalab.tubble.entity.components.ComponentMappers;
import com.carvalab.tubble.entity.entities.Ball;
import com.carvalab.tubble.entity.entities.Launcher;
import com.carvalab.tubble.entity.entities.Wall;
import com.carvalab.tubble.entity.systems.PhysicsAnimationRenderSystem;
import com.carvalab.tubble.entity.systems.TextureRenderSystem;

public class TubbleGameplayTest extends ApplicationAdapter {
	SpriteBatch			batch;
	World				world;
	Engine				ashley;
	OrthographicCamera	camera;
	float scrWidth = 0f;
	float scrHeight = 0f;

	Vector2				ballSpawnPoint;
	Launcher			ballLauncher;
	float				ballShootForce		= 10f;
	float				ballStartForce		= 10f;
	float				ballMaxShootForce	= 100f;

	@Override
	public void create() {
		scrWidth = Gdx.graphics.getWidth();
		scrHeight = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(scrWidth, scrHeight);
		camera.translate(scrWidth / 2, scrHeight / 2);
		ashley = new Engine();
		Assets.load();

		batch = new SpriteBatch();
		// New world with -1f down gravity and objects that do sleep
		world = new World(new Vector2(0.0f, -1.0f), true);

		// Add the animation rendering system
		ashley.addSystem(new PhysicsAnimationRenderSystem(batch));
		ashley.addSystem(new TextureRenderSystem(batch));

		// Add map walls
		// Floor
		ashley.addEntity(Wall.instantiate(world, new Vector2(0f, 0f), new Vector2(scrWidth, 0f)));
		// Left wall
		ashley.addEntity(Wall.instantiate(world, new Vector2(0f, 0f), new Vector2(0f, scrHeight)));
		// Right wall
		ashley.addEntity(Wall.instantiate(world, new Vector2(scrWidth - 1f, 0f), new Vector2(scrWidth - 1f,
				scrHeight)));
		// Ceiling
		ashley.addEntity(Wall.instantiate(world, new Vector2(0f, scrHeight - 1f), new Vector2(scrWidth,
				scrHeight - 1f)));

		// Make the ball spawnPoint
		ballSpawnPoint = new Vector2(scrWidth / 2, 40f);
		ballLauncher = new Launcher(ballSpawnPoint);

		Gdx.input.setInputProcessor(new InputAdapter() {

			@Override
			public boolean touchDown(int x, int y, int pointer, int button) {
				return true;
			}

			@Override
			public boolean touchUp(int x, int y, int pointer, int button) {
				Ball b = spawnBall(ballSpawnPoint);
				ComponentMappers.physics.get(b).getBody()
				.applyForceToCenter(
						ballSpawnPoint.cpy().sub(new Vector2(x, y)).scl(-1).nor().scl(ballShootForce),
						true);
				ballShootForce = ballStartForce;
				return true;
			}
		});
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1.0f);
		Gdx.gl.glClear(16384);

		batch.setProjectionMatrix(camera.combined);
		camera.update();

		world.step(0.016f, 6, 2);

		ashley.update(Gdx.graphics.getDeltaTime());

		ballLauncher.lookTo(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

		if (Gdx.input.isButtonPressed(Buttons.LEFT) && ballShootForce < ballMaxShootForce) {
			ballShootForce += 1;
			ballLauncher.setChargePercent((ballShootForce - ballStartForce)
					/ (ballMaxShootForce - ballStartForce));
		}

		batch.begin();
		ballLauncher.draw(batch);
		batch.end();

		// Physics.debug(world, camera);
	}

	public Ball spawnBall(Vector2 Pos) {
		Ball b = Ball.instantiate(world, Pos, 0.1f);
		ashley.addEntity(b);
		return b;
	}

	@Override
	public void dispose() {}
}