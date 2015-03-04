package me.embarker.starworks.game;

public class Player {
	public static int LIVES = 0;
	public static int SCORE = 0;
	public static int HIGH_SCORE = 0;
	
	public static float FIREWORK_SPEED_MODIFIER = 1.0F; // Speed multiplier for the firework
	public static float FIREWORK_SPAWN_MODIFIER = 1.0F; // Spawn rate multiplier for the firework.

	public static float FIREWORK_SPAWN_INCREASE_RATE = 0.025F; // Amount increased for firework spawn rates every second.
	public static float FIREWORK_SPEED_INCREASE_RATE = 0.04F; // Speed increased for fireworks every second.
	
	public static void startNewGame() {
		LIVES = 5;
		SCORE = 0;
		FIREWORK_SPEED_MODIFIER = 1.0F;
		FIREWORK_SPAWN_MODIFIER = 1.0F;
	}
}
