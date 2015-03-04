package me.embarker.starworks;

import me.embarker.starworks.game.FireworkRender;
import me.embarker.starworks.game.Player;
import me.embarker.starworks.screen.game.GameScreen;
import me.embarker.starworks.util.Assets;

import com.badlogic.gdx.Game;

public class Starworks extends Game {
	public static Starworks game;
	
	@Override
	public void create() {
		game = this;
		
		// Create game objects on start.
		Assets.init();
		FireworkRender.init();
		Player.init();
		
		// Set first screen.
		this.setScreen(new GameScreen());
	}
}
