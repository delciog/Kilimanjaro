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

public class MainMenuScreen extends GLScreen {
	Camera2D guiCam;
	SpriteBatcher batcher;
	Rectangle soundBounds;
	Rectangle playBounds;
	Rectangle highscoresBounds;
	Rectangle helpBounds;
	Vector2 touchPoint;
	
	public MainMenuScreen(Game game) {
		super(game);
		guiCam = new Camera2D(glGraphics, 320, 480);
		batcher = new SpriteBatcher(glGraphics, 100);
		soundBounds = new Rectangle(0, 0, 64, 64);
		playBounds = new Rectangle(180 - 5, 200 - 5,  60 + 10, 20 + 10);
		helpBounds = new Rectangle(220 - 5, 100 - 5, 90 + 10, 20 + 10);
		
		
		touchPoint = new Vector2();
	}
	
	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			
			if (event.type == TouchEvent.TOUCH_UP) {
				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);
				
				if (OverlapTester.pointInRectangle(playBounds, touchPoint)) {
					Assets.playSound(Assets.clickSound);
					game.setScreen(new GameScreen(game));
					return;
				}

				if (OverlapTester.pointInRectangle(helpBounds, touchPoint)) {
					Assets.playSound(Assets.clickSound);
					game.setScreen(new HelpScreen(game));
					return;
				}
				if (OverlapTester.pointInRectangle(soundBounds, touchPoint)) {
					Assets.playSound(Assets.clickSound);
					Settings.soundEnabled = !Settings.soundEnabled;
					if (Settings.soundEnabled) {
						Assets.music.play();
					}
					else {
						Assets.music.pause();
					}
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
		
//		batcher.drawSprite(160, 480 - 10 - 71, 274, 142, Assets.logo);
		batcher.drawSprite(160, 480 - 10 - 71, 236, 80, Assets.logo);
		//batcher.drawSprite(160, 200, 300, 110, Assets.mainMenu);
		batcher.drawSprite(180, 200, 60, 20, Assets.playMenu);
		//batcher.drawSprite(160, 250, 148, 18, Assets.highscoresMenu);
		batcher.drawSprite(220, 100, 90, 20, Assets.helpMenu);
		batcher.drawSprite(32, 32, 29, 29, Settings.soundEnabled ? Assets.soundOn : Assets.soundOff);
		
		batcher.endBatch();
		
		gl.glDisable(GL10.GL_BLEND);
	}
	
	@Override
	public void pause() {
		Settings.save(game.getFileIO());
	}
	
	@Override
	public void resume() { }
	
	@Override
	public void dispose() { }
}
