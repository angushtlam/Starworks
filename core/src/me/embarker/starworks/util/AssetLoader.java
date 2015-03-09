package me.embarker.starworks.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLoader {
	private static AssetManager mng;
	
	public static void init() {
		TextureParameter tParam = new TextureParameter();
		tParam.minFilter = TextureFilter.Linear;
		tParam.magFilter = TextureFilter.Nearest;
		
		mng = new AssetManager();
		mng.load("ui/uiskin.json", Skin.class);
		
		mng.load("game/bg.jpg", Texture.class, tParam);
		mng.load("game/atmosphere.png", Texture.class, tParam);
		mng.load("game/terrain/building/1.png", Texture.class, tParam);
		mng.load("game/star/1.png", Texture.class, tParam);
		mng.load("game/star/2.png", Texture.class, tParam);
		mng.load("game/star/3.png", Texture.class, tParam);
		mng.load("game/star/4.png", Texture.class, tParam);
		mng.load("game/star/5.png", Texture.class, tParam);
		mng.load("game/star/fragment.png", Texture.class, tParam);
		mng.load("game/firework/head.png", Texture.class, tParam);
		mng.load("game/firework/trail.png", Texture.class, tParam);
		mng.load("game/firework/bloom.png", Texture.class, tParam);
		mng.load("game/ui/logo.png", Texture.class, tParam);
		mng.load("game/ui/options.jpg", Texture.class, tParam);

		mng.load("sound/break1.mp3", Sound.class);
		mng.load("sound/break2.mp3", Sound.class);
		mng.load("sound/break3.mp3", Sound.class);
		mng.load("sound/miss.mp3", Sound.class);
		mng.load("sound/music.mp3", Music.class);
		
	}
	
	public static AssetManager getManager() {
		return mng;
	}
}
