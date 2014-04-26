// Kilimanjaro Game - http://delciolab.wordpress.com
// This game is based on the game "Super Jumper", 
// written by Mario Zechner and described in a book published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// I also recommend the libgdx implementation of "Super Jumper" as a reference

package com.delciolab.androidgames.kilimanjaro;

import com.delciolab.androidgames.framework.GameObject;

public class Tent extends GameObject {
	public static float TENT_WIDTH = 0.7f;
	public static float TENT_HEIGHT = 0.7f;
	
	public Tent(float x, float y) {
		super(x, y, TENT_WIDTH, TENT_HEIGHT);
	}
}
