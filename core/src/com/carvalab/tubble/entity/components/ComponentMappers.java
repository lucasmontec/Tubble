package com.carvalab.tubble.entity.components;

import com.badlogic.ashley.core.ComponentMapper;

public class ComponentMappers
{
  public static final ComponentMapper<PhysicsComponent> physics = ComponentMapper.getFor(PhysicsComponent.class);
  public static final ComponentMapper<AnimationComponent> animation = ComponentMapper.getFor(AnimationComponent.class);
}