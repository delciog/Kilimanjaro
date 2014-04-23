// Kilimanjaro Game - http://delciolab.wordpress.com
// This game is based on the game "Super Jumper", 
// written by Mario Zechner and described in a book published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// I also recommend the libgdx implementation of "Super Jumper" as a reference

package com.delciolab.androidgames.kilimanjaro;

import com.delciolab.androidgames.framework.GameObject;

public class Backpack extends GameObject {
	public static final float BACKPACK_WIDTH = 0.3f;
	public static final float BACKPACK_HEIGHT = 0.4f;
	public static final float BACKPACK_SCORE = 10;
	
	float stateTime;
	
	public Backpack(float x, float y) {
		super(x, y, BACKPACK_WIDTH, BACKPACK_HEIGHT);
		stateTime = 0;
	}
	
	public void update(float deltaTime) {
		stateTime += deltaTime;
	}
}
