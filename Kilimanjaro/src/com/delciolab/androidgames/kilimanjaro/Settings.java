// Kilimanjaro Game - http://delciolab.wordpress.com
// This game is based on the game "Super Jumper", 
// written by Mario Zechner and described in a book published by Apress in April 2011
// https://code.google.com/p/beginning-android-games/source/browse/trunk/ch03-game-framework/src/com/badlogic/androidgames/framework/?r=2
// I also recommend the libgdx implementation of "Super Jumper" as a reference

package com.delciolab.androidgames.kilimanjaro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.delciolab.androidgames.framework.FileIO;

public class Settings {
	public static boolean soundEnabled = true;
	public final static int[] highScores = new int[] {100, 80, 50, 30, 10};
	public final static String file = ".kilimanjaro";
	
	public static void load(FileIO files) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(files.readFile(file)));
			soundEnabled = Boolean.parseBoolean(in.readLine());
			for (int i = 0; i < 5; i++) {
				highScores[i] = Integer.parseInt(in.readLine());
			}
		} catch (IOException e) {
			// No problem as there are defaults
		} catch (NumberFormatException e) {
			// No problem as there are defaults
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {}
		}
	}
	
	public static void save(FileIO files) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(files.writeFile(file)));
			out.write(Boolean.toString(soundEnabled));
			out.write("\n");
			for (int i = 0; i < 5; i++) {
				out.write(Integer.toString(highScores[i]));
				out.write("\n");
			}
		} catch (IOException e) {
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {}
		}
	}
	
	public static void addScore(int score) {
		for (int i = 0; i < 5; i++) {
			if (highScores[i] < score) {
				for (int j = 4; j > i; j--) {
					highScores[j] = highScores[j-1];
				}
				highScores[i] = score;
				break;
			}
		}
	}
}
