package me.embarker.starworks.tracker;

import java.util.Random;

import me.embarker.starworks.util.Assets;

public class SoundEffect {
	private static Random rand = new Random();
	
	public static void playStarSound(float vol, float pitch) {
		switch(1 + rand.nextInt(3)) {
			case 1:
				Assets.SOUND_BREAK_1.play(vol, pitch, 1F);
				break;
			case 2:
				Assets.SOUND_BREAK_2.play(vol, pitch, 1F);
				break;
			case 3:
				Assets.SOUND_BREAK_3.play(vol, pitch, 1F);
				break;
		}
	}
	
	
	public static void playStarSound() {
		playStarSound(0.5F, 1.5F);
	}
	
	public static void playUISound() {
		playStarSound(0.25F, 0.75F);
	}
}
