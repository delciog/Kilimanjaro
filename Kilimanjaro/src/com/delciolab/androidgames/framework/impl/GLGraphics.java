// Android games framework - http://delciolab.wordpress.com
// This framework is based on the book "Beginning Android Games", 
// written by Mario Zechner, published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// If you are looking for a Java games framework, I recommend: libgdx

package com.delciolab.androidgames.framework.impl;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;

public class GLGraphics {
	GLSurfaceView glView;
	GL10 gl;
	
	public GLGraphics(GLSurfaceView glView) {
		this.glView = glView;
	}
	
	public GL10 getGL() {
		return gl;
	}
	
	void setGL(GL10 gl) {
		this.gl = gl;
	}

	public int getWidth() {
		return glView.getWidth();
	}
	
	public int getHeight() {
		return glView.getHeight();
	}
}
