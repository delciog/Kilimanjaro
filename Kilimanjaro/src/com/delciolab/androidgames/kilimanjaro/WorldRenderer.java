// Kilimanjaro Game - http://delciolab.wordpress.com
// This game is based on the game "Super Jumper", 
// written by Mario Zechner and described in a book published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// I also recommend the libgdx implementation of "Super Jumper" as a reference

package com.delciolab.androidgames.kilimanjaro;

import javax.microedition.khronos.opengles.GL10;

import com.delciolab.androidgames.framework.gl.Animation;
import com.delciolab.androidgames.framework.gl.Camera2D;
import com.delciolab.androidgames.framework.gl.SpriteBatcher;
import com.delciolab.androidgames.framework.gl.TextureRegion;
import com.delciolab.androidgames.framework.impl.GLGraphics;

public class WorldRenderer {
	static final float FRUSTUM_WIDTH = 10;
	static final float FRUSTUM_HEIGHT = 15;
	GLGraphics glGraphics;
	World world;
	Camera2D cam;
	SpriteBatcher batcher;
	
	public WorldRenderer(GLGraphics glGraphics, SpriteBatcher batcher, World world) {
		this.glGraphics = glGraphics;
		this.world = world;
		this.cam = new Camera2D(glGraphics, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.batcher = batcher;
	}
	
	public void render() {
		if (world.kuba.position.y > cam.position.y) {
			cam.position.y = world.kuba.position.y;
		}
		cam.setViewportAndMatrices();
		renderBackground();
		renderObjects();
	}

	private void renderBackground() {
		batcher.beginBatch(Assets.background);
		batcher.drawSprite(cam.position.x, cam.position.y, FRUSTUM_WIDTH, FRUSTUM_HEIGHT, Assets.backgroundRegion);
		batcher.endBatch();
	}

	private void renderObjects() {
		GL10 gl = glGraphics.getGL();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		batcher.beginBatch(Assets.items);
		renderKuba();
		renderPlatforms();
		renderItems();
		renderBirds();
		renderFinalGoal();
		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);
	}

	private void renderKuba() {
		TextureRegion keyFrame;
		switch (world.kuba.state) {
		case Kuba.KUBA_STATE_FALL:
			keyFrame = Assets.kubaFall.getKeyFrame(world.kuba.stateTime, Animation.ANIMATION_LOOPING);
			break;
		case Kuba.KUBA_STATE_JUMP:
			keyFrame = Assets.kubaJump.getKeyFrame(world.kuba.stateTime, Animation.ANIMATION_LOOPING);
			break;
		case Kuba.KUBA_STATE_HIT:
		default:
			keyFrame = Assets.kubaHit;
		}
		
		float side = world.kuba.velocity.x < 0 ? -1 : 1;
		batcher.drawSprite(world.kuba.position.x, world.kuba.position.y, side * 1, 1, keyFrame);
	}

	private void renderPlatforms() {
		int len = world.platforms.size();
		for (int i = 0; i < len; i++) {
			Platform platform = world.platforms.get(i);
			TextureRegion keyFrame = Assets.platform;
			if (platform.state == Platform.PLATFORM_STATE_PULVERIZING) {
				keyFrame = Assets.breakingPlatform.getKeyFrame(platform.stateTime, Animation.ANIMATION_NONLOOPING);
			}
			batcher.drawSprite(platform.position.x, platform.position.y, 2, 0.5f, keyFrame);
		}
	}

	private void renderItems() {
		int len = world.tents.size();
		for (int i = 0; i < len; i++) {
			Tent tent = world.tents.get(i);
			batcher.drawSprite(tent.position.x, tent.position.y + 0.1f, 1.4f, 1.4f, Assets.tent);
		}
		
		len = world.backpacks.size();
		for (int i = 0; i < len; i++) {
			Backpack backPack = world.backpacks.get(i);
			TextureRegion keyFrame = Assets.backpackAnim.getKeyFrame(backPack.stateTime,  Animation.ANIMATION_LOOPING);
			batcher.drawSprite(backPack.position.x, backPack.position.y, 0.6f , 0.6f, keyFrame);
		}
	}

	private void renderBirds() {
		int len = world.birds.size();
		for (int i = 0; i < len; i++) {
			Bird squirrel = world.birds.get(i);
			TextureRegion keyFrame = Assets.birdFly.getKeyFrame(squirrel.stateTime, Animation.ANIMATION_LOOPING);
			float side = squirrel.velocity.x < 0 ? -1 : 1;
			batcher.drawSprite(squirrel.position.x, squirrel.position.y, side * 0.8f, 0.8f, keyFrame);
		}
	}

	private void renderFinalGoal() {
		FinalGoal castle = world.finalgoal;
		batcher.drawSprite(castle.position.x, castle.position.y, 2, 2, Assets.finalGoal);
	}
}
