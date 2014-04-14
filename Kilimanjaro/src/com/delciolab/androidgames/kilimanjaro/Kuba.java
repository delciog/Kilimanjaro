// Kilimanjaro Game - http://delciolab.wordpress.com
// This game is based on the game "Super Jumper", 
// written by Mario Zechner and described in a book published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// I also recommend the libgdx implementation of "Super Jumper" as a reference

package com.delciolab.androidgames.kilimanjaro;

import com.delciolab.androidgames.framework.DynamicGameObject;

public class Kuba extends DynamicGameObject {
	public static final int KUBA_STATE_JUMP = 0;
	public static final int KUBA_STATE_FALL = 1;
	public static final int KUBA_STATE_HIT = 2;
	public static final float KUBA_JUMP_VELOCITY = 11;
	public static final float KUBA_MOVE_VELOCITY = 20;
	public static final float KUBA_WIDTH = 0.8f;
	public static final float KUBA_HEIGHT = 0.8f;
	
	int state;
	float stateTime;
	
	public Kuba(float x, float y) {
		super(x, y, KUBA_WIDTH, KUBA_HEIGHT);
		state = KUBA_STATE_FALL;
		stateTime = 0;
	}
	
	public void update(float deltaTime) {
		velocity.add(World.gravity.x * deltaTime, World.gravity.y * deltaTime);
		position.add(velocity.x  * deltaTime, velocity.y * deltaTime);
		bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
		
		if (velocity.y > 0 && state != KUBA_STATE_HIT) {
			if (state != KUBA_STATE_JUMP) {
				state = KUBA_STATE_JUMP;
				stateTime = 0;
			}
		}
		
		if (velocity.y < 0 && state != KUBA_STATE_HIT) {
			if (state != KUBA_STATE_FALL) {
				state = KUBA_STATE_FALL;
				stateTime = 0;
			}
		}
		
		if (position.x < 0) {
			position.x = World.WORLD_WIDTH;
		}
		if (position.x > World.WORLD_WIDTH) {
			position.x = 0;
		}
		
		stateTime += deltaTime;
	}
	
	public void hitSquirrel() {
		velocity.set(0, 0);
		state = KUBA_STATE_HIT;
	}
	
	public void hitPlatform() {
		velocity.y = KUBA_JUMP_VELOCITY;
		state = KUBA_STATE_JUMP;
	}
	
	public void hitSpring() {
		velocity.y = KUBA_MOVE_VELOCITY * 1.5f;
		state = KUBA_STATE_JUMP;
		stateTime = 0;
	}
}
