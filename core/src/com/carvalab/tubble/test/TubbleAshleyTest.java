package com.carvalab.tubble.test;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.carvalab.tubble.Assets;
import com.carvalab.tubble.Physics;
import com.carvalab.tubble.entity.entities.Ball;
import com.carvalab.tubble.entity.entities.Wall;
import com.carvalab.tubble.entity.systems.PhysicsAnimationRenderSystem;

public class TubbleAshleyTest extends ApplicationAdapter {
	SpriteBatch			batch;
	World				world;
	Engine				ashley;
	OrthographicCamera	camera;

	@Override
	public void create() {
		camera = new OrthographicCamera(800.0f, 600.0f);
		camera.translate(400.0f, 300.0f);
		ashley = new Engine();
		Assets.load();

		batch = new SpriteBatch();
		world = new World(new Vector2(0.0f, -10.0f), true);

		ashley.addSystem(new PhysicsAnimationRenderSystem(batch));

		ashley.addEntity(Wall.instantiate(world, new Vector2(0.0f, 0.0f), new Vector2(800.0f, 0.0f)));
		ashley.addEntity(Wall.instantiate(world, new Vector2(0.0f, 0.0f), new Vector2(0.0f, 600.0f)));
		ashley.addEntity(Wall.instantiate(world, new Vector2(799.0f, 0.0f), new Vector2(799.0f, 600.0f)));

		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean touchDown(int x, int y, int pointer, int button) {
				TubbleAshleyTest.this.spawnBall(new Vector2(x, Gdx.graphics.getHeight() - y));
				return true;
			}

			@Override
			public boolean touchUp(int x, int y, int pointer, int button) {
				return true;
			}
		});
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(16384);

		batch.setProjectionMatrix(camera.combined);
		camera.update();

		world.step(0.016f, 6, 2);

		ashley.update(Gdx.graphics.getDeltaTime());

		Physics.debug(world, camera);
	}

	public void spawnBall(Vector2 Pos) {
		ashley.addEntity(Ball.instantiate(world, Pos, 0.2f));
	}

	@Override
	public void dispose() {}
}
