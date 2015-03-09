package me.embarker.starworks.tracker;

import me.embarker.starworks.util.Assets;

public class BGM {
	public static void start() {
		Assets.MUSIC.setLooping(true);
		Assets.MUSIC.setVolume(0.75F);
		check();
	}
	
	public static void check() {
		if (Player.PLAY_MUSIC) {
			Assets.MUSIC.play();
		} else {
			Assets.MUSIC.pause();
		}
	}
}
