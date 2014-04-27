// Kilimanjaro Game - http://delciolab.wordpress.com
// This game is based on the game "Super Jumper", 
// written by Mario Zechner and described in a book published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// I also recommend the libgdx implementation of "Super Jumper" as a reference

package com.delciolab.androidgames.kilimanjaro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.delciolab.androidgames.framework.math.OverlapTester;
import com.delciolab.androidgames.framework.math.Vector2;

public class World {
	public interface WorldListener {
		public void jump();
		public void highJump();
		public void hit();
		public void backpack();
	}
		
	public static final float WORLD_WIDTH = 10;
	public static final float WORLD_HEIGHT = 15 * 20;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	public static final Vector2 gravity = new Vector2(0, -12);
	
	public final Kuba kuba;
	public final List<Platform> platforms;
	public final List<Tent> tents;
	public final List<Bird> birds;
	public final List<Backpack> backpacks;
	public FinalGoal finalgoal;
	public final WorldListener listener;
	public final Random rand;
	
	public float heightSoFar;
	public int score;
	public int state;
	
	public World(WorldListener listener) {
		this.kuba = new Kuba(5, 1);
		this.platforms = new ArrayList<Platform>();
		this.tents = new ArrayList<Tent>();
		this.birds = new ArrayList<Bird>();
		this.backpacks = new ArrayList<Backpack>();
		this.listener = listener;
		rand = new Random();
		generateLevel();
		
		this.heightSoFar = 0;
		this.score = 0;
		this.state = WORLD_STATE_RUNNING;
	}
	
	private void generateLevel() {
		float y = Platform.PLATFORM_HEIGHT / 2;
		float maxJumpHeigth = Kuba.KUBA_JUMP_VELOCITY * Kuba.KUBA_JUMP_VELOCITY / (2 * -gravity.y);
		while (y < WORLD_HEIGHT - WORLD_WIDTH / 2) {
			int type = rand.nextFloat() > 0.8f ? Platform.PLATFORM_TYPE_MOVING : Platform.PLATFORM_TYPE_STATIC;
			float x = rand.nextFloat() * (WORLD_WIDTH - Platform.PLATFORM_WIDTH) + Platform.PLATFORM_WIDTH / 2;
			
			Platform platform = new Platform(type, x, y);
			platforms.add(platform);
			
			if (rand.nextFloat() > 0.9f && type != Platform.PLATFORM_TYPE_MOVING) {
				Tent tent = new Tent(platform.position.x, platform.position.y + Platform.PLATFORM_HEIGHT / 2 + Tent.TENT_HEIGHT / 2);
				tents.add(tent);
			}
			
			if (y > WORLD_HEIGHT / 3 && rand.nextFloat() > 0.8f) {
				Bird bird = new Bird(platform.position.x + rand.nextFloat(), platform.position.y + Bird.BIRD_HEIGHT + rand.nextFloat() * 2);
				birds.add(bird);
			}
			
			if (rand.nextFloat() > 0.6f) {
				Backpack backPack = new Backpack(platform.position.x + rand.nextFloat(), platform.position.y + Backpack.BACKPACK_HEIGHT + rand.nextFloat() * 3);
				backpacks.add(backPack);
			}
			
			y += (maxJumpHeigth - 0.5f);
			y -= rand.nextFloat() * (maxJumpHeigth / 3);
		}
		finalgoal = new FinalGoal(WORLD_WIDTH / 2, y);
	}

	public void update(float deltaTime, float accelX) {
		updateKuba(deltaTime, accelX);
		updatePlatforms(deltaTime);
		updateBirds(deltaTime);
		updateBackpacks(deltaTime);
		if (kuba.state != Kuba.KUBA_STATE_HIT) {
			checkCollisions();
		}
		checkGameOver();
	}
	
	public void updateKuba(float deltaTime, float accelX) {
		if (kuba.state != Kuba.KUBA_STATE_HIT && kuba.position.y <= 0.5f) {
			kuba.hitPlatform();
		}
		if (kuba.state != Kuba.KUBA_STATE_HIT) {
			kuba.velocity.x = -accelX / 10 * Kuba.KUBA_MOVE_VELOCITY;
		}
		kuba.update(deltaTime);
		heightSoFar = Math.max(kuba.position.y, heightSoFar);
	}
	
	private void updatePlatforms(float deltaTime) {
		int len = platforms.size();
		for (int i = 0; i < len; i++) {
			Platform platform = platforms.get(i);
			platform.update(deltaTime);
			if (platform.state == Platform.PLATFORM_STATE_PULVERIZING && platform.stateTime > Platform.PLATFORM_PULVERIZE_TIME) {
				platforms.remove(platform);
				len = platforms.size();
			}
		}
	}
	
	private void updateBirds(float deltaTime) {
		int len = birds.size();
		for (int i = 0; i < len; i++) {
			Bird bird = birds.get(i);
			bird.update(deltaTime);
		}
	}
	
	private void updateBackpacks(float deltaTime) {
		int len = backpacks.size();
		for (int i = 0; i < len; i++) {
			Backpack backpack = backpacks.get(i);
			backpack.update(deltaTime);
		}
	}
	
	private void checkCollisions() {
		checkPlatformCollisions();
		checkBirdCollisions();
		checkItemCollisions();
		checkFinalGoalCollisions();
	}

	private void checkPlatformCollisions() {
		if (kuba.velocity.y > 0) {
			return;
		}
		
		int len = platforms.size();
		for (int i = 0; i < len; i++) {
			Platform platform = platforms.get(i);
			if (kuba.position.y > platform.position.y) {
				if (OverlapTester.overlapRectangles(kuba.bounds, platform.bounds)) {
					kuba.hitPlatform();
					listener.jump();
					if(rand.nextFloat() > 0.5f) {
						platform.pulverize();
					}
					break;
				}
			}
		}
	}

	private void checkBirdCollisions() {
		int len = birds.size();
		for (int i = 0; i < len; i++) {
			Bird bird = birds.get(i);
			if (OverlapTester.overlapRectangles(bird.bounds, kuba.bounds)) {
				kuba.hitSquirrel();
				listener.hit();
			}
		}
	}

	private void checkItemCollisions() {
		int len = backpacks.size();
		for (int i = 0; i < len; i++) {
			Backpack backpack = backpacks.get(i);
			if (OverlapTester.overlapRectangles(kuba.bounds, backpack.bounds)) {
				backpacks.remove(backpack);
				len = backpacks.size();
				score += Backpack.BACKPACK_SCORE;
			}
		}
		if (kuba.velocity.y > 0) {
			return;
		}
		
		len = tents.size();
		for (int i = 0; i < len; i++) {
			Tent tent = tents.get(i);
			if (kuba.position.y > tent.position.y) {
				if (OverlapTester.overlapRectangles(kuba.bounds, tent.bounds)) {
					kuba.hitSpring();
					listener.highJump();
				}
			}
		}
	}

	private void checkFinalGoalCollisions() {
		if (OverlapTester.overlapRectangles(finalgoal.bounds, kuba.bounds)) {
			state = WORLD_STATE_NEXT_LEVEL;
		}
	}
	
	private void checkGameOver() {
		if (heightSoFar - 7.5f > kuba.position.y) {
			state = WORLD_STATE_GAME_OVER;
		}
	}
}
