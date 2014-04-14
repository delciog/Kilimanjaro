// Android games framework - http://delciolab.wordpress.com
// This framework is based on the book "Beginning Android Games", 
// written by Mario Zechner, published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// If you are looking for a Java games framework, I recommend: libgdx

package com.delciolab.androidgames.framework;

public interface Graphics {
	public static enum PixmapFormat {
		ARGB8888, ARGB4444, RGB565
	}
	
	public Pixmap newPixmap(String fileName, PixmapFormat format);
	
	public void clear(int color);
	
	public void drawPixel(int x, int y, int color);
	
	public void drawLine(int x, int y, int x2, int y2, int color);
	
	public void drawRect(int x, int y, int width, int height, int color);
	
	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight);
	
	public void drawPixmap(Pixmap pixmap, int x, int y);
	
	public int getWidth();
	
	public int getHeight();
}
