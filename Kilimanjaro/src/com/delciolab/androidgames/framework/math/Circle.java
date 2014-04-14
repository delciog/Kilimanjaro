// Android games framework - http://delciolab.wordpress.com
// This framework is based on the book "Beginning Android Games", 
// written by Mario Zechner, published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// If you are looking for a Java games framework, I recommend: libgdx

package com.delciolab.androidgames.framework.math;

public class Circle {
	public final Vector2 center = new Vector2();
	public float radius;
	
	public Circle(float x, float y, float radius){
		this.center.set(x, y);
		this.radius = radius;
	}
}
