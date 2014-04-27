// Kilimanjaro Game - http://delciolab.wordpress.com
// This game is based on the game "Super Jumper", 
// written by Mario Zechner and described in a book published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// I also recommend the libgdx implementation of "Super Jumper" as a reference

package com.delciolab.androidgames.kilimanjaro;

import com.delciolab.androidgames.framework.Music;
import com.delciolab.androidgames.framework.Sound;
import com.delciolab.androidgames.framework.gl.Animation;
import com.delciolab.androidgames.framework.gl.Font;
import com.delciolab.androidgames.framework.gl.Texture;
import com.delciolab.androidgames.framework.gl.TextureRegion;
import com.delciolab.androidgames.framework.impl.GLGame;

public class Assets {
	public static Texture background;
	public static TextureRegion backgroundRegion;
	
	public static Texture items;
	public static TextureRegion mainMenu;
	public static TextureRegion playMenu;
	public static TextureRegion highscoresMenu;
	public static TextureRegion helpMenu;
	public static TextureRegion pauseMenu;
	public static TextureRegion ready;
	public static TextureRegion gameOver;
	public static TextureRegion highScoresRegion;
	public static TextureRegion logo;
	public static TextureRegion soundOn;
	public static TextureRegion soundOff;
	public static TextureRegion arrow;
	public static TextureRegion pause;
	public static TextureRegion tent;
	public static TextureRegion finalGoal;
	public static Animation backpackAnim;
	public static Animation kubaJump;
	public static Animation kubaFall;
	public static TextureRegion kubaHit;
	public static Animation birdFly;
	public static TextureRegion platform;
	public static Animation breakingPlatform;
	public static Font font;
	
	public static Music music;
	public static Sound jumpSound;
	public static Sound highJumpSound;
	public static Sound hitSound;
	public static Sound backPackSound;
	public static Sound clickSound;
	
	public static void load(GLGame game) {
		background = new Texture(game, "background.png");
		backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
		items = new Texture(game, "items.png");
		mainMenu = new TextureRegion(items, 300, 66, 28, 9);
		playMenu = new TextureRegion(items, 303, 66, 28, 9);  
		highscoresMenu = new TextureRegion(items, 0, 234, 74, 8);  
		helpMenu = new TextureRegion(items, 258, 66, 43, 9); 
		pauseMenu = new TextureRegion(items, 238, 223, 48, 9);
		ready = new TextureRegion(items, 254, 54, 44, 10);
		gameOver = new TextureRegion(items, 0, 244, 70, 9);
		highScoresRegion = new TextureRegion(Assets.items, 0, 234, 74, 8);
		logo = new TextureRegion(items, 0, 152, 236, 80);
		soundOff = new TextureRegion(items, 314, 140, 29, 29);
		soundOn = new TextureRegion(items, 288, 224, 29, 29);
		arrow = new TextureRegion(items, 287, 109, 29, 29);
		pause = new TextureRegion(items, 287, 77, 29, 29);
		tent = new TextureRegion(items, 238, 152, 74, 54);
		finalGoal = new TextureRegion(items, 255, 0, 62, 52);
		
		backpackAnim = new Animation(0.2f, new TextureRegion(items, 319, 77, 20, 20), // ok - consider anim?
										new TextureRegion(items, 319, 77, 20, 20),
										new TextureRegion(items, 319, 77, 20, 20),
										new TextureRegion(items, 319, 77, 20, 20));
		
		kubaJump = new Animation(0.2f, new TextureRegion(items, 257, 113, 30, 34),
                						new TextureRegion(items, 257, 77, 30, 34));
		
		kubaFall = new Animation(0.2f, new TextureRegion(items, 257, 113, 30, 34),
										new TextureRegion(items, 257, 77, 30, 34));
		
		kubaHit = new TextureRegion(items, 257, 77, 30, 34);
		
		birdFly = new Animation(0.2f, new TextureRegion(items, 319, 0, 28, 27),
		                    			new TextureRegion(items, 314, 171, 28, 28),
		                    			new TextureRegion(items, 319, 0, 28, 27),
		                    			new TextureRegion(items, 314, 171, 28, 28));
		
		platform = new TextureRegion(items, 204, 234, 62, 14);
		
		breakingPlatform = new Animation(0.2f, new TextureRegion(items, 204, 234, 62, 14), // ok
					                     new TextureRegion(items, 140, 234, 62, 14),
					                     new TextureRegion(items, 76, 234, 62, 13),
					                     new TextureRegion(items, 238, 208, 60, 14));
		
		font = new Font(items, 0, -1, 16, 16, 25);

		music = game.getAudio().newMusic("music.mp3");
		music.setLooping(true);
		music.setVolume(1.0f);
		if (Settings.soundEnabled) {
			music.play();
		}
		jumpSound = game.getAudio().newSound("jump.wav");
		highJumpSound = game.getAudio().newSound("highjump.ogg");
		hitSound = game.getAudio().newSound("crow.wav");
		backPackSound = game.getAudio().newSound("coin.ogg");
		clickSound = game.getAudio().newSound("click.wav");
	}
	
	public static void reload() {
		background.reload();
		items.reload();
		if (Settings.soundEnabled) {
			music.play();
		}
		else
		{
			music.pause();
			music.stop();
		}
	}
	
	public static void playSound(Sound sound) {
		if (Settings.soundEnabled) {
			sound.play(1);
		}
	}
}
