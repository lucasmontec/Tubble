package com.carvalab.tubble.entity.components;

import com.badlogic.ashley.core.Component;

public class LivingStateComponent extends Component {

	/**
	 * Living states. This is used to control updating and removal
	 * of entities. Can be used to choose animations.
	 * 
	 * @author Lucas M Carvalhaes
	 *
	 */
	public enum STATE {
		ALIVE,
		DIYNG,
		DEAD
	}

	private STATE	state	= STATE.ALIVE;

	/**
	 * Returns the current state
	 * 
	 * @return the state
	 */
	public STATE getState() {
		return state;
	}

	/**
	 * Changes the state
	 * 
	 * @param state
	 *            the state to set
	 */
	public void setState(STATE state) {
		this.state = state;
	}

}
