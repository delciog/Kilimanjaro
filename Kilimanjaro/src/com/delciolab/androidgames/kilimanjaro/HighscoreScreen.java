// Kilimanjaro Game - http://delciolab.wordpress.com
// This game is based on the game "Super Jumper", 
// written by Mario Zechner and described in a book published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// I also recommend the libgdx implementation of "Super Jumper" as a reference

package com.delciolab.androidgames.kilimanjaro;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.delciolab.androidgames.framework.Game;
import com.delciolab.androidgames.framework.Input.TouchEvent;
import com.delciolab.androidgames.framework.gl.Camera2D;
import com.delciolab.androidgames.framework.gl.SpriteBatcher;
import com.delciolab.androidgames.framework.impl.GLScreen;
import com.delciolab.androidgames.framework.math.OverlapTester;
import com.delciolab.androidgames.framework.math.Rectangle;
import com.delciolab.androidgames.framework.math.Vector2;

public class HighscoreScreen extends GLScreen {
	Camera2D guiCam;
	SpriteBatcher batcher;
	Rectangle backBounds;
	Vector2 touchPoint;
	String[] highScores;
	float xOffset = 0;
	
	public HighscoreScreen(Game game) {
		super(game);
		
		guiCam = new Camera2D(glGraphics, 320, 480);
		backBounds = new Rectangle(0, 0, 64, 64);
		touchPoint = new Vector2();
		batcher = new SpriteBatcher(glGraphics, 100);
		highScores = new String[5];
		for (int i = 0; i < 5; i++) {
			highScores[i] = (i + 1) + ". " + Settings.highScores[i];
			xOffset = Math.max(highScores[i].length() * Assets.font.glyphWidth, xOffset);
		}
		xOffset = 160 - xOffset / 2;
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			touchPoint.set(event.x, event.y);
			guiCam.touchToWorld(touchPoint);
			
			if (event.type == TouchEvent.TOUCH_UP) {
				if (OverlapTester.pointInRectangle(backBounds, touchPoint)) {
					game.setScreen(new MainMenu(game));
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		batcher.beginBatch(Assets.background);
		batcher.drawSprite(160, 240, 320, 480, Assets.backgroundRegion);
		batcher.endBatch();
		
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		batcher.beginBatch(Assets.items);
		batcher.drawSprite(160, 360, 300, 33, Assets.highScoresRegion);
		
		float y = 320;
		for (int i = 4; i  >= 0; i--) {
			Assets.font.drawText(batcher, highScores[i], xOffset, y);
			y += Assets.font.glyphHeight;
		}
		
		batcher.drawSprite(32, 32, 64, 64, Assets.arrow);
		batcher.endBatch();
	}

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void dispose() {	}
	
	
}