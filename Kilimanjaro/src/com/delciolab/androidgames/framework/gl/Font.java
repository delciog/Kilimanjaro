// Android games framework - http://delciolab.wordpress.com
// This framework is based on the book "Beginning Android Games", 
// written by Mario Zechner, published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// If you are looking for a Java games framework, I recommend: libgdx

package com.delciolab.androidgames.framework.gl;

public class Font {
	public final Texture texture;
	public final int glyphWidth;
	public final int glyphHeight;
	public final TextureRegion[] glyphs = new TextureRegion[96];
	
	public Font(Texture texture, int offsetX, int offsetY, int glyphPerRow, int glyphWidth, int glyphHeight) {
		this.texture = texture;
		this.glyphWidth = glyphWidth;
		this.glyphHeight = glyphHeight;
		int x = offsetX;
		int y = offsetY;
		for (int i = 0; i < 96; i++) {
			glyphs[i] = new TextureRegion(texture, x, y, glyphWidth, glyphHeight);
			x += glyphWidth;
			if (x == offsetX + glyphPerRow * glyphWidth) {
				x = offsetX;
				y += glyphHeight;
			}
		}
	}
	
	public void drawText(SpriteBatcher batcher, String text, float x, float y) {
		int len = text.length();
		for (int i = 0; i < len; i++) {
			int c = text.charAt(i) - ' ';
			if (c < 0 || c > glyphs.length - 1) {
				continue;
			}
			
			TextureRegion glyph = glyphs[c];
			batcher.drawSprite(x, y, glyphWidth, glyphHeight, glyph);
			x += glyphWidth;
		}
	}
}
