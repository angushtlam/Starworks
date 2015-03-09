package me.embarker.starworks;

import me.embarker.starworks.screen.loading.LoadingScreen;
import me.embarker.starworks.util.AssetLoader;

import com.badlogic.gdx.Game;

public class Starworks extends Game {
	public static Starworks game;
	
	@Override
	public void create() {
		game = this;
		
		AssetLoader.init(); // Prepare to load all assets
		this.setScreen(new LoadingScreen()); // Enter loading screen to load all assets.
		
	}
}
