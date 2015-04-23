package com.carvalab.tubble.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent extends Component {

	private Vector2	position;

	public PositionComponent() {
		position = new Vector2();
	}

	public PositionComponent(Vector2 pos) {
		position = new Vector2(pos);
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public float X() {
		return position.x;
	}

	public float Y() {
		return position.y;
	}

}
