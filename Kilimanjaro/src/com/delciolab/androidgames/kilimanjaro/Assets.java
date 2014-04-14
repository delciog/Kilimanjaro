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
	public static TextureRegion pauseMenu;
	public static TextureRegion ready;
	public static TextureRegion gameOver;
	public static TextureRegion highScoresRegion;
	public static TextureRegion logo;
	public static TextureRegion soundOn;
	public static TextureRegion soundOff;
	public static TextureRegion arrow;
	public static TextureRegion pause;
	public static TextureRegion spring;
	public static TextureRegion castle;
	public static Animation coinAnim;
	public static Animation kubaJump;
	public static Animation kubaFall;
	public static TextureRegion kubaHit;
	public static Animation squirrelFly;
	public static TextureRegion platform;
	public static Animation breakingPlatform;
	public static Font font;
	
	public static Music music;
	public static Sound jumpSound;
	public static Sound highJumpSound;
	public static Sound hitSound;
	public static Sound coinSound;
	public static Sound clickSound;
	
	public static void load(GLGame game) {
		background = new Texture(game, "background.png");
		backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
		
		items = new Texture(game, "items.png");
		mainMenu = new TextureRegion(items, 300, 66, 28, 9); //ok 
		pauseMenu = new TextureRegion(items, 238, 224, 48, 8); //ok ???????????
		ready = new TextureRegion(items, 255, 54, 44, 10); //ok
		gameOver = new TextureRegion(items, 0, 244, 70, 9); //ok
		highScoresRegion = new TextureRegion(Assets.items, 0, 234, 74, 8); //ok
		logo = new TextureRegion(items, 0, 152, 236, 80); //ok ???????????
		soundOff = new TextureRegion(items, 314, 140, 30, 29); //ok
		soundOn = new TextureRegion(items, 288, 224, 30, 29); //ok
		arrow = new TextureRegion(items, 287, 109, 30, 29); //ok
		pause = new TextureRegion(items, 287, 77, 30, 29); //ok
		
		spring = new TextureRegion(items, 238, 152, 74, 54); //ok
		castle = new TextureRegion(items, 255, 0, 62, 52); //ok
		
		coinAnim = new Animation(0.2f, new TextureRegion(items, 319, 77, 20, 20), // ok - consider anim?
										new TextureRegion(items, 319, 77, 20, 20),
										new TextureRegion(items, 319, 77, 20, 20),
										new TextureRegion(items, 319, 77, 20, 20));
		kubaJump = new Animation(0.2f, new TextureRegion(items, 255, 113, 30, 34),
                						new TextureRegion(items, 255, 77, 30, 34));
		kubaFall = new Animation(0.2f, new TextureRegion(items, 255, 113, 30, 34),
										new TextureRegion(items, 255, 77, 30, 34));
		kubaHit = new TextureRegion(items, 255, 77, 30, 34);
		squirrelFly = new Animation(0.2f, new TextureRegion(items, 319, 0, 28, 27),
		                    			new TextureRegion(items, 314, 171, 28, 28));
		platform = new TextureRegion(items, 204, 234, 62, 14);
		breakingPlatform = new Animation(0.2f, new TextureRegion(items, 204, 234, 62, 14), // ok
					                     new TextureRegion(items, 140, 234, 62, 14),
					                     new TextureRegion(items, 76, 234, 62, 13),
					                     new TextureRegion(items, 238, 208, 60, 14));
		
		font = new Font(items, 0, 0, 16, 13, 18);

//		items = new Texture(game, "items.png");
//		mainMenu = new TextureRegion(items, 0, 224, 300, 110); 
//		pauseMenu = new TextureRegion(items, 224, 128, 192, 96);
//		ready = new TextureRegion(items, 320, 224, 192, 32);
//		gameOver = new TextureRegion(items, 352, 256, 160, 96);
//		highScoresRegion = new TextureRegion(Assets.items, 0, 257, 300, 110 / 3);
//		logo = new TextureRegion(items, 0, 352, 274, 142);
//		soundOff = new TextureRegion(items, 0, 0, 64, 64);
//		soundOn = new TextureRegion(items, 64, 0, 64, 64);
//		arrow = new TextureRegion(items, 0, 64, 64, 64);
//		pause = new TextureRegion(items, 64, 64, 64, 64);
//		
//		spring = new TextureRegion(items, 128, 0, 32, 32);
//		castle = new TextureRegion(items, 128, 64, 64, 64);
//		
//		coinAnim = new Animation(0.2f, new TextureRegion(items, 128, 32, 32, 32),
//										new TextureRegion(items, 160, 32, 32, 32),
//										new TextureRegion(items, 192,  32,  32,  32),
//										new TextureRegion(items, 160, 32, 32, 32));
//		kubaJump = new Animation(0.2f, new TextureRegion(items, 0, 128, 32, 32),
//                						new TextureRegion(items, 32, 128, 32, 32));
//		kubaFall = new Animation(0.2f, new TextureRegion(items, 64, 128, 32, 32),
//                						new TextureRegion(items, 96, 128, 32, 32));
//		kubaHit = new TextureRegion(items, 128, 128, 32, 32);
//		squirrelFly = new Animation(0.2f, new TextureRegion(items, 0, 160, 32, 32),
//		                    			new TextureRegion(items, 32, 160, 32, 32));
//		platform = new TextureRegion(items, 64, 160, 64, 16);
//		breakingPlatform = new Animation(0.2f, new TextureRegion(items, 64, 160, 64, 16),
//					                     new TextureRegion(items, 64, 176, 64, 16),
//					                     new TextureRegion(items, 64, 192, 64, 16),
//					                     new TextureRegion(items, 64, 208, 64, 16));
//		
//		font = new Font(items, 224, 0, 16, 16, 20);
//	
//		
		music = game.getAudio().newMusic("music.mp3");
		music.setLooping(true);
		music.setVolume(0.5f);
		if (Settings.soundEnabled) {
			music.play();
		}
		jumpSound = game.getAudio().newSound("jump.ogg");
		highJumpSound = game.getAudio().newSound("highjump.ogg");
		hitSound = game.getAudio().newSound("hit.ogg");
		coinSound = game.getAudio().newSound("coin.ogg");
		clickSound = game.getAudio().newSound("click.ogg");
	}
	
	public static void reload() {
		background.reload();
		items.reload();
		if (Settings.soundEnabled) {
			music.play();
		}
	}
	
	public static void playSound(Sound sound) {
		if (Settings.soundEnabled) {
			sound.play(1);
		}
	}
}
