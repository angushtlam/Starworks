package me.embarker.starworks.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Player {
	public static Preferences pref;
	
	public static int LIVES = 0;
	public static int SCORE = 0;
	public static int HIGH_SCORE = 0;
	public static boolean HIGH_SCORE_SAVED = false;
	
	public static boolean PLAY_MUSIC = true;
	public static boolean PLAY_SFX = true;
	public static boolean GAME_PAUSED = false;

	public static float FIREWORK_SPAWN_INCREASE_RATE = 0.325F; // Amount increased for firework spawn rates every second.
	public static float FIREWORK_SPEED_INCREASE_RATE = 0.125F; // Speed increased for fireworks every second.
	public static int FIREWORK_CHANGE_RATE_INCREMENT = 15; // Score needed to add difficulty.
	
	public static void init() {
		pref = Gdx.app.getPreferences("starworks-player");
		update();
	}
	
	public static void resetScore() {
		LIVES = 3;
		SCORE = 0;
		
	}
	
	public static void save() {
		pref.putInteger("highscore", HIGH_SCORE);
		pref.putBoolean("sfx", PLAY_SFX);
		pref.flush(); // Save changes to file.
	}
	
	public static void update() {
		HIGH_SCORE = pref.getInteger("highscore");
		PLAY_SFX = pref.getBoolean("sfx");
	}
}
