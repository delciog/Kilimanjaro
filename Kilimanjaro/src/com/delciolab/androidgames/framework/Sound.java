// Android games framework - http://delciolab.wordpress.com
// This framework is based on the book "Beginning Android Games", 
// written by Mario Zechner, published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// If you are looking for a Java games framework, I recommend: libgdx

package com.delciolab.androidgames.framework;

public interface Sound {
	public void play(float volume);
//	public void stop();
//	public void pause();
//	public void setLooping(boolean looping);
//	public void setVolume(float volume);
//	public boolean isPlaying();
//	public boolean isStopped();
//	public boolean isLooping();
	public void dispose();
}
