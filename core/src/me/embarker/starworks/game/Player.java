package me.embarker.starworks.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Player {
	public static Preferences pref;
	
	public static int LIVES = 0;
	public static int SCORE = 0;
	public static int HIGH_SCORE = 0;
	public static boolean HIGH_SCORE_SAVED = false;
	
	public static float FIREWORK_SPEED_MODIFIER = 1.0F; // Speed multiplier for the firework
	public static float FIREWORK_SPAWN_MODIFIER = 1.0F; // Spawn rate multiplier for the firework.

	public static float FIREWORK_SPAWN_INCREASE_RATE = 0.085F; // Amount increased for firework spawn rates every second.
	public static float FIREWORK_SPEED_INCREASE_RATE = 0.07F; // Speed increased for fireworks every second.
	
	public static void init() {
		pref = Gdx.app.getPreferences("starworks-player");
		update();
	}
	
	public static void startNewGame() {
		LIVES = 3;
		SCORE = 0;
		FIREWORK_SPEED_MODIFIER = 1.0F;
		FIREWORK_SPAWN_MODIFIER = 1.0F;
		
	}
	
	public static void save() {
		pref.putInteger("highscore", HIGH_SCORE);
		pref.flush(); // Save changes to file.
	}
	
	public static void update() {
		HIGH_SCORE = pref.getInteger("highscore");
	}
}
