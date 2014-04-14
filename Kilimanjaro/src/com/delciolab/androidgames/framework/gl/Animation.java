// Android games framework - http://delciolab.wordpress.com
// This framework is based on the book "Beginning Android Games", 
// written by Mario Zechner, published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// If you are looking for a Java games framework, I recommend: libgdx

package com.delciolab.androidgames.framework.gl;

public class Animation {
	public static final int ANIMATION_LOOPING = 0;
	public static final int ANIMATION_NONLOOPING = 1;
	
	final TextureRegion[] keyFrames;
	final float frameDuration;
	
	public Animation(float frameDuration, TextureRegion ... keyFrames) {
		this.frameDuration = frameDuration;
		this.keyFrames = keyFrames;
	}
	
	public TextureRegion getKeyFrame(float stateTime, int mode) {
		int frameNumber = (int)(stateTime / frameDuration);
		
		if (mode == ANIMATION_LOOPING) {
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);
		}
		else {
			frameNumber = frameNumber % keyFrames.length;
		}
		
		return keyFrames[frameNumber];
	}
}
