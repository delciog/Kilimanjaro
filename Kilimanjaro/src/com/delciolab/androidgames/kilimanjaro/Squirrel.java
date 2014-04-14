// Kilimanjaro Game - http://delciolab.wordpress.com
// This game is based on the game "Super Jumper", 
// written by Mario Zechner and described in a book published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// I also recommend the libgdx implementation of "Super Jumper" as a reference

package com.delciolab.androidgames.kilimanjaro;

import com.delciolab.androidgames.framework.DynamicGameObject;

public class Squirrel extends DynamicGameObject {
	public static final float SQUIRREL_WIDTH = 1;
	public static final float SQUIRREL_HEIGHT = 0.6f;
	public static final float SQUIRREL_VELOCITY = 3f;
	
	float stateTime = 0;
	
	public Squirrel(float x, float y) {
		super(x, y, SQUIRREL_WIDTH, SQUIRREL_HEIGHT);
		velocity.set(SQUIRREL_VELOCITY, 0);
	}
	
	public void update(float deltaTime) {
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.lowerLeft.set(position).sub(SQUIRREL_WIDTH / 2, SQUIRREL_HEIGHT / 2);
		
		if (position.x < SQUIRREL_WIDTH / 2) {
			position.x = SQUIRREL_WIDTH / 2;
			velocity.x = SQUIRREL_VELOCITY;
		}
		
		if (position.x > World.WORLD_WIDTH - SQUIRREL_WIDTH / 2) {
			position.x = World.WORLD_WIDTH - SQUIRREL_WIDTH / 2;
			velocity.x = -SQUIRREL_VELOCITY;
		}
		
		stateTime += deltaTime;
	}
}
