package com.carvalab.tubble.entity.components;

import com.badlogic.ashley.core.ComponentMapper;

public class ComponentMappers {
	public static final ComponentMapper<PhysicsComponent>	physics		= ComponentMapper
			.getFor(PhysicsComponent.class);
	public static final ComponentMapper<AnimationComponent>	animation	= ComponentMapper
			.getFor(AnimationComponent.class);
	public static final ComponentMapper<PositionComponent>	position	= ComponentMapper
			.getFor(PositionComponent.class);
	public static final ComponentMapper<TextureComponent>	texture		= ComponentMapper
			.getFor(TextureComponent.class);
}
