// Android games framework - http://delciolab.wordpress.com
// This framework is based on the book "Beginning Android Games", 
// written by Mario Zechner, published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// If you are looking for a Java games framework, I recommend: libgdx

package com.delciolab.androidgames.framework.impl;

import android.graphics.Bitmap;

import com.delciolab.androidgames.framework.Graphics.PixmapFormat;
import com.delciolab.androidgames.framework.Pixmap;

public class AndroidPixmap implements Pixmap {
	Bitmap bitmap;
	PixmapFormat format;
	
	public AndroidPixmap(Bitmap bitmap, PixmapFormat format) {
		this.bitmap = bitmap;
		this.format = format;
	}
	
	@Override
	public int getWidth() {
		return bitmap.getWidth();
	}
	@Override
	public int getHeight() {
		return bitmap.getHeight();
	}
	@Override
	public PixmapFormat getFormat() {
		return format;
	}
	@Override
	public void dispose() {
		bitmap.recycle();
	}
	
	
}
