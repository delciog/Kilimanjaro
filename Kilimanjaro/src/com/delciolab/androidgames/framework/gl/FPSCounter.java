// Android games framework - http://delciolab.wordpress.com
// This framework is based on the book "Beginning Android Games", 
// written by Mario Zechner, published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// If you are looking for a Java games framework, I recommend: libgdx

package com.delciolab.androidgames.framework.gl;

import android.util.Log;

public class FPSCounter {
	long startTime = System.nanoTime();
	int frames = 0;
	
	public void logFrame() {
		frames++;
		if (System.nanoTime() - startTime >= 1000000000) {
			Log.d("FPSCounter", "fps: " + frames);
			frames = 0;
			startTime = System.nanoTime();
		}
	}
}
