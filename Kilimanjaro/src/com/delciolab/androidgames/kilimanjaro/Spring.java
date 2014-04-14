// Kilimanjaro Game - http://delciolab.wordpress.com
// This game is based on the game "Super Jumper", 
// written by Mario Zechner and described in a book published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// I also recommend the libgdx implementation of "Super Jumper" as a reference

package com.delciolab.androidgames.kilimanjaro;

import com.delciolab.androidgames.framework.GameObject;

public class Spring extends GameObject {
	public static float SPRING_WIDTH = 1.0f;
	public static float SPRING_HEIGHT = 1.0f;
	
	public Spring(float x, float y) {
		super(x, y, SPRING_WIDTH, SPRING_HEIGHT);
	}
}
