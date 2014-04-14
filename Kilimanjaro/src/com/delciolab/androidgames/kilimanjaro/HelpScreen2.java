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
import com.delciolab.androidgames.framework.gl.Texture;
import com.delciolab.androidgames.framework.gl.TextureRegion;
import com.delciolab.androidgames.framework.impl.GLScreen;
import com.delciolab.androidgames.framework.math.OverlapTester;
import com.delciolab.androidgames.framework.math.Rectangle;
import com.delciolab.androidgames.framework.math.Vector2;

public class HelpScreen2 extends GLScreen {
	Camera2D guiCam;
	SpriteBatcher batcher;
	Rectangle nextBounds;
	Vector2 touchPoint;
	Texture helpImage;
	TextureRegion helpRegion;
	
	public HelpScreen2(Game game) {
		super(game);
		
		guiCam = new Camera2D(glGraphics, 320, 480);
		nextBounds = new Rectangle(320 - 64,  0, 64, 64);
		touchPoint = new Vector2();
		batcher = new SpriteBatcher(glGraphics, 1);
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
				if (OverlapTester.pointInRectangle(nextBounds, touchPoint)) {
					Assets.playSound(Assets.clickSound);
					game.setScreen(new HelpScreen3(game));
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
		
		batcher.beginBatch(helpImage);
		batcher.drawSprite(160, 240, 320, 480, helpRegion);
		batcher.endBatch();
		
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		batcher.beginBatch(Assets.items);
		batcher.drawSprite(320 - 32, 32, -64, 64, Assets.arrow);
		batcher.endBatch();
		
		gl.glDisable(GL10.GL_BLEND);
	}

	@Override
	public void pause() {
		helpImage.dispose();
	}

	@Override
	public void resume() {
		helpImage = new Texture(glGame, "help2.png");
		helpRegion = new TextureRegion(helpImage, 0, 0, 320, 480);
	}

	@Override
	public void dispose() { }
}
