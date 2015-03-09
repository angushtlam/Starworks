package me.embarker.starworks.screen.loading;

import me.embarker.starworks.Starworks;
import me.embarker.starworks.screen.game.GameScreen;
import me.embarker.starworks.util.AssetLoader;
import me.embarker.starworks.util.Assets;
import me.embarker.starworks.util.LabelMaker;
import me.embarker.starworks.util.Resolution;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LoadingScreen extends ScreenAdapter {
	private Viewport viewport;
	public Stage stage;
	
	private Label lblLoading;
	private Label lblLoadingPrc;
	
	// Ran when object is initialised.
	public LoadingScreen() {
		viewport = new ExtendViewport(
				Resolution.GAME_WIDTH_16_9, Resolution.GAME_HEIGHT,
				Resolution.GAME_WIDTH_1_1, Resolution.GAME_HEIGHT);

		stage = new Stage();
		stage.setViewport(viewport);
		
		loadSkin(); // Skin needs to be loaded first for labels.
		lblLoading = new LabelMaker("LOADING").getLabel();
		lblLoadingPrc = new LabelMaker("0%").getLabel();
		
		Table table = new Table();
		table.setFillParent(true);
		table.add(lblLoading).expandX().center();
		table.row();
		table.add(lblLoadingPrc).expandX().center();
		stage.addActor(table);
	}
	
	@Override
	public void show() {
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        AssetManager mng = AssetLoader.getManager();
        
		if (mng.update()) { // Keep loading until all assets are loaded.
			Assets.init();
			Starworks.game.setScreen(new GameScreen()); // Switch screens.
		}

		lblLoadingPrc.setText((int) (mng.getProgress() * 100) + "%");
        
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		// Updates the viewport to the screen, then centers the camera.
		viewport.update(width, height, true);
		Resolution.GAME_WIDTH_CURRENT = (int) viewport.getCamera().viewportWidth;
	}
	
	@Override
	public void hide() {
	}
	
	private void loadSkin() {
		Assets.SKIN = new Skin(Gdx.files.internal("ui/uiskin.json"));
	}
}
