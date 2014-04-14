// Kilimanjaro Game - http://delciolab.wordpress.com
// This game is based on the game "Super Jumper", 
// written by Mario Zechner and described in a book published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// I also recommend the libgdx implementation of "Super Jumper" as a reference

package com.delciolab.androidgames.kilimanjaro;

//import android.view.VelocityTracker;

import com.delciolab.androidgames.framework.DynamicGameObject;

public class Platform extends DynamicGameObject {
	public static final float PLATFORM_WIDTH = 2;
	public static final float PLATFORM_HEIGHT = 0.5f;
	public static final int PLATFORM_TYPE_STATIC = 0;
	public static final int PLATFORM_TYPE_MOVING = 1;
	public static final int PLATFORM_STATE_NORMAL = 0;
	public static final int PLATFORM_STATE_PULVERIZING = 1;
	public static final float PLATFORM_PULVERIZE_TIME = 0.2f * 4;
	public static final float PLATFORM_VELOCITY = 2;
	
	int type;
	int state;
	float stateTime;
	
	public Platform(int type, float x, float y) {
		super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
		this.type = type;
		this.state = PLATFORM_STATE_NORMAL;
		this.stateTime = 0;
		if (type == PLATFORM_TYPE_MOVING) {
			velocity.x = PLATFORM_VELOCITY;
		}
	}
	
	public void update(float deltaTime) {
		if (type == PLATFORM_TYPE_MOVING) {
			position.add(velocity.x * deltaTime, 0);
			bounds.lowerLeft.set(position).sub(PLATFORM_WIDTH / 2, PLATFORM_HEIGHT / 2);
		}
		
		if (position.x < PLATFORM_WIDTH / 2) {
			velocity.x = -velocity.x;
			position.x = PLATFORM_WIDTH / 2;
		}
		
		if (position.x > World.WORLD_WIDTH - PLATFORM_WIDTH / 2) {
			velocity.x = -velocity.x;
			position.x = World.WORLD_WIDTH - PLATFORM_WIDTH / 2;
		}
		
		stateTime += deltaTime;
	}
	
	public void pulverize() {
		state = PLATFORM_STATE_PULVERIZING;
		stateTime = 0;
		velocity.x = 0;
	}
}
