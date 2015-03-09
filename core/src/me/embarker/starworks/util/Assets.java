package me.embarker.starworks.util;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
	public static Texture GAME_BG, GAME_ATMOS;
	
	public static Texture GAME_BLD_1;
	
	public static Texture GAME_STAR_1, GAME_STAR_2, GAME_STAR_3, GAME_STAR_4, GAME_STAR_5, GAME_STAR_FRAG;

	public static Texture FIREWORK_HEAD;
	public static Texture FIREWORK_TRAIL;
	public static Texture FIREWORK_BLOOM;
	
	public static Texture UI_START, UI_OPTIONS;
	
	public static Sound SOUND_MISS, SOUND_BREAK_1, SOUND_BREAK_2, SOUND_BREAK_3;
	public static Music MUSIC;
	
	public static Skin SKIN;
	
	public static void init() {
		GAME_BG = AssetLoader.getManager().get("game/bg.jpg", Texture.class);
		GAME_ATMOS = AssetLoader.getManager().get("game/atmosphere.png", Texture.class);
		
		GAME_BLD_1 = AssetLoader.getManager().get("game/terrain/building/1.png", Texture.class);
		
		GAME_STAR_1 = AssetLoader.getManager().get("game/star/1.png", Texture.class);
		GAME_STAR_2 = AssetLoader.getManager().get("game/star/2.png", Texture.class);
		GAME_STAR_3 = AssetLoader.getManager().get("game/star/3.png", Texture.class);
		GAME_STAR_4 = AssetLoader.getManager().get("game/star/4.png", Texture.class);
		GAME_STAR_5 = AssetLoader.getManager().get("game/star/5.png", Texture.class);
		GAME_STAR_FRAG = AssetLoader.getManager().get("game/star/fragment.png", Texture.class);

		FIREWORK_HEAD = AssetLoader.getManager().get("game/firework/head.png", Texture.class);
		FIREWORK_TRAIL = AssetLoader.getManager().get("game/firework/trail.png", Texture.class);
		FIREWORK_BLOOM = AssetLoader.getManager().get("game/firework/bloom.png", Texture.class);

		UI_START = AssetLoader.getManager().get("game/ui/logo.png", Texture.class);
		UI_OPTIONS = AssetLoader.getManager().get("game/ui/options.jpg", Texture.class);
		
		SOUND_BREAK_1 = AssetLoader.getManager().get("sound/break1.mp3", Sound.class);
		SOUND_BREAK_2 = AssetLoader.getManager().get("sound/break2.mp3", Sound.class);
		SOUND_BREAK_3 = AssetLoader.getManager().get("sound/break3.mp3", Sound.class);
		SOUND_MISS = AssetLoader.getManager().get("sound/miss.mp3", Sound.class);

		MUSIC = AssetLoader.getManager().get("sound/music.mp3", Music.class);
		
		// Skin needs to be loaded first for labels.
		// Skin is loaded in LoadingScreen.
		//SKIN = AssetLoader.getManager().get("ui/uiskin.json", Skin.class);

	}
}
