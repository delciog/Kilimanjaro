// Kilimanjaro Game - http://delciolab.wordpress.com
// This game is based on the game "Super Jumper", 
// written by Mario Zechner and described in a book published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// I also recommend the libgdx implementation of "Super Jumper" as a reference

package com.delciolab.androidgames.kilimanjaro;

import com.delciolab.androidgames.framework.DynamicGameObject;

public class Bird extends DynamicGameObject {
	public static final float BIRD_WIDTH = 1;
	public static final float BIRD_HEIGHT = 0.6f;
	public static final float BIRD_VELOCITY = 3f;
	
	float stateTime;
	
	public Bird(float x, float y) {
		super(x, y, BIRD_WIDTH, BIRD_HEIGHT);
		velocity.set(BIRD_VELOCITY, 0);
	}
	
	public void update(float deltaTime) {
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.lowerLeft.set(position).sub(BIRD_WIDTH / 2, BIRD_HEIGHT / 2);
		
		if (position.x < BIRD_WIDTH / 2) {
			position.x = BIRD_WIDTH / 2;
			velocity.x = BIRD_VELOCITY;
		}
		
		if (position.x > World.WORLD_WIDTH - BIRD_WIDTH / 2) {
			position.x = World.WORLD_WIDTH - BIRD_WIDTH / 2;
			velocity.x = -BIRD_VELOCITY;
		}
		
		stateTime += deltaTime;
	}
}
