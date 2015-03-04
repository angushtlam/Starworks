package me.embarker.starworks.game;

public class GameTracker {
	public static float FIREWORK_SPEED_MODIFIER = 1.0F; // Speed multiplier for the firework
	public static float FIREWORK_SPAWN_MODIFIER = 1.0F; // Spawn rate multiplier for the firework.
	
	public static boolean FIRST_FIREWORK_LABEL = false;
	public static boolean SPEEDUP_FIREWORK_LABEL = false;
	
	public static void startNewGame() {
		FIREWORK_SPEED_MODIFIER = 1.0F;
		FIREWORK_SPAWN_MODIFIER = 1.0F;
		
		FIRST_FIREWORK_LABEL = true;
		
	}
}
