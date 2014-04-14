// Kilimanjaro Game - http://delciolab.wordpress.com
// This game is based on the game "Super Jumper", 
// written by Mario Zechner and described in a book published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// I also recommend the libgdx implementation of "Super Jumper" as a reference

package com.delciolab.androidgames.kilimanjaro;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.delciolab.androidgames.framework.Screen;
import com.delciolab.androidgames.framework.impl.GLGame;

public class Kilimanjaro extends GLGame {
	boolean firstTimeCreate = true;
	
	@Override
	public Screen getStartScreen() {
		return new MainMenuScreen(this);
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
		if (firstTimeCreate) {
			Settings.load(getFileIO());
			Assets.load(this);
			firstTimeCreate = false;
		} 
		else {
			Assets.reload();
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if (Settings.soundEnabled) {
			Assets.music.pause();
		}
	}
}
