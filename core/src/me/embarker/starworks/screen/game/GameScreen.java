package me.embarker.starworks.screen.game;

import me.embarker.starworks.tracker.Player;

import com.badlogic.gdx.ScreenAdapter;

public class GameScreen extends ScreenAdapter {
	private GameLogic logic;
	private GameUI ui;
	public GameRenderer renderer;
	
	// Ran when object is initialised.
	public GameScreen() {
		// Make logic and renderer and set them to a private variable.
		ui = new GameUI();
		logic = new GameLogic(ui);
		renderer = new GameRenderer(logic, ui);
		
	}
	
	@Override
	public void show() {
		renderer.show();
	}
	
	@Override
	public void render(float delta) {
		logic.update(delta);
		renderer.render();
	}
	
	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);
	}
	
	@Override
	public void hide() {
	}
	
	@Override
	public void pause() {
		Player.LIVES = 0; // kill em xd
	}
	
	@Override
	public void resume() {
	}
}
