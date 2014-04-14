// Android games framework - http://delciolab.wordpress.com
// This framework is based on the book "Beginning Android Games", 
// written by Mario Zechner, published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// If you are looking for a Java games framework, I recommend: libgdx

package com.delciolab.androidgames.framework.impl;

import java.io.IOException;


import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.delciolab.androidgames.framework.Audio;
import com.delciolab.androidgames.framework.Music;
import com.delciolab.androidgames.framework.Sound;

public class AndroidAudio implements Audio {
	AssetManager assets;
	SoundPool soundPool;
	
	public AndroidAudio(Activity activity) {
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool= new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}
	
	@Override
	public Music newMusic(String filename) {
		try {
			AssetFileDescriptor assetDescriptor = assets.openFd(filename);
			return new AndroidMusic(assetDescriptor);
		} 
		catch (IOException e) {
			throw new RuntimeException("Couldn't load music: " + filename);
		}
	}
	
	@Override
	public Sound newSound(String filename) {
		try {
			AssetFileDescriptor assetdesDescriptor = assets.openFd(filename);
			int soundId = soundPool.load(assetdesDescriptor, 0);
			return new AndroidSound(soundPool, soundId);
		} 
		catch (IOException e) {
			throw new RuntimeException("Couldn't load sound: " + filename);
		}
	}
}
