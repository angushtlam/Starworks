package me.embarker.starworks.tracker;

public class GameTracker {
	public static float FIREWORK_SPEED_MODIFIER = 1.0F; // Speed multiplier for the firework
	public static float FIREWORK_SPAWN_MODIFIER = 1.0F; // Spawn rate multiplier for the firework.
	
	public static boolean FIRST_FIREWORK_LABEL = false;
	public static boolean SPEEDUP_FIREWORK_LABEL = false;
	public static boolean SLOWDOWN_FIREWORK_LABEL = false;
	
	public static boolean GAME_IN_PROGRESS = false;
	public static boolean SHOW_START_BUTTON = false;
	
	public static void startNewGame() {
		FIREWORK_SPEED_MODIFIER = 1.0F;
		FIREWORK_SPAWN_MODIFIER = 1.0F;
		
		FIRST_FIREWORK_LABEL = true;
		
		GAME_IN_PROGRESS = true;
		
		Player.GAME_PAUSED = false; // Fuck your pause
		
	}
}
