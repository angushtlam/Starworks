package me.embarker.starworks.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
	public static Texture GAME_BG, GAME_ATMOS;
	public static Texture GAME_BLD_1;
	
	public static Texture GAME_STAR_1, GAME_STAR_2, GAME_STAR_3, GAME_STAR_4, GAME_STAR_5, GAME_STAR_FRAG;

	public static Texture FIREWORK_HEAD;
	public static Texture FIREWORK_TRAIL;
	public static Texture FIREWORK_BLOOM;
	
	public static Texture UI_LIFE_ALIVE, UI_LIFE_DEAD;
	
	public static Skin SKIN;
	
	public static void init() {
		GAME_BG = getTexture(Gdx.files.internal("game/bg.jpg"));
		GAME_ATMOS = getTexture(Gdx.files.internal("game/atmosphere.png"));
		
		GAME_BLD_1 = getTexture(Gdx.files.internal("game/terrain/building/1.png"));
		
		GAME_STAR_1 = getTexture(Gdx.files.internal("game/star/1.png"));
		GAME_STAR_2 = getTexture(Gdx.files.internal("game/star/2.png"));
		GAME_STAR_3 = getTexture(Gdx.files.internal("game/star/3.png"));
		GAME_STAR_4 = getTexture(Gdx.files.internal("game/star/4.png"));
		GAME_STAR_5 = getTexture(Gdx.files.internal("game/star/5.png"));
		GAME_STAR_FRAG = getTexture(Gdx.files.internal("game/star/fragment.png"));

		FIREWORK_HEAD = getTexture(Gdx.files.internal("game/firework/head.png"));
		FIREWORK_TRAIL = getTexture(Gdx.files.internal("game/firework/trail.png"));
		FIREWORK_BLOOM = getTexture(Gdx.files.internal("game/firework/bloom.png"));
		
		UI_LIFE_ALIVE = getTexture(Gdx.files.internal("game/ui/life/alive.png"));
		UI_LIFE_DEAD = getTexture(Gdx.files.internal("game/ui/life/dead.png"));
		
		SKIN = new Skin(Gdx.files.internal("ui/uiskin.json"));
		
	}
	
	private static Texture getTexture(FileHandle handle) {
		Texture texture = new Texture(handle);
		texture.setFilter(TextureFilter.Linear, TextureFilter.Nearest); // Change scaling so textures resize properly.
		
		return texture;
	}
	
}
