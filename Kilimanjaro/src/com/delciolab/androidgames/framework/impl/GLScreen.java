// Android games framework - http://delciolab.wordpress.com
// This framework is based on the book "Beginning Android Games", 
// written by Mario Zechner, published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// If you are looking for a Java games framework, I recommend: libgdx

package com.delciolab.androidgames.framework.impl;

import com.delciolab.androidgames.framework.Game;
import com.delciolab.androidgames.framework.Screen;

public abstract class GLScreen extends Screen {
	protected final GLGraphics glGraphics;
	protected final GLGame glGame;
	
	public GLScreen(Game game) {
		super(game);
		glGame = (GLGame)game;
		glGraphics = ((GLGame)game).getGLGraphics();
	}
}
