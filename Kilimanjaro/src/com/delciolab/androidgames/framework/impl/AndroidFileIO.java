// Android games framework - http://delciolab.wordpress.com
// This framework is based on the book "Beginning Android Games", 
// written by Mario Zechner, published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// If you are looking for a Java games framework, I recommend: libgdx

package com.delciolab.androidgames.framework.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//import android.content.Context;
//import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
//import android.preference.PreferenceManager;

import com.delciolab.androidgames.framework.FileIO;

public class AndroidFileIO implements FileIO {
	//Context context;
	AssetManager assets;
	String externalStoragePath;
	
	public AndroidFileIO(AssetManager assets) {
		//this.context = context;
		this.assets = assets;
		this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	}
	
	@Override
	public InputStream readAsset(String fileName) throws IOException {
		return assets.open(fileName);
	}
	
	@Override
	public InputStream readFile(String fileName) throws IOException {
		return new FileInputStream(externalStoragePath + fileName);
	}
	
	@Override
	public OutputStream writeFile(String fileName) throws IOException {
		return new FileOutputStream(externalStoragePath + fileName);
	}
	
//	public SharedPreferences getPreferences() {
//		return PreferenceManager.getDefaultSharedPreferences(context);
//	}
}
