package me.embarker.starworks.screen.game;

import me.embarker.starworks.game.Player;
import me.embarker.starworks.render.StarManager;
import me.embarker.starworks.render.TerrainManager;
import me.embarker.starworks.util.Assets;
import me.embarker.starworks.util.ImageMaker;
import me.embarker.starworks.util.LabelMaker;
import me.embarker.starworks.util.Resolution;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameRenderer {
	private Viewport viewport;
	public Stage stage;

	private TextButton btnNewGame;
	
	public GameRenderer(GameLogic logic) {
		viewport = new ExtendViewport(
				Resolution.GAME_WIDTH_16_9, Resolution.GAME_HEIGHT,
				Resolution.GAME_WIDTH_1_1, Resolution.GAME_HEIGHT);

		stage = new Stage();
		stage.setViewport(viewport);

		stage.addActor(logic.bg);
		stage.addActor(logic.groupStars);
		stage.addActor(logic.groupFireworks);
		stage.addActor(new ImageMaker(Assets.GAME_ATMOS, 0, 0).getImage());
		stage.addActor(logic.groupTerrain);
		
		makeUi();
		addUiActions();
		
		// Render UI
		Table tableUI = new Table();
		tableUI.setFillParent(true);
		//table.setDebug(true);
		
		Table tableBtn = new Table();
		//tableBtn.setDebug(true);
		tableBtn.add(btnNewGame).width(160);
		tableUI.add(tableBtn).expandX().padBottom(10);
		
		tableUI.bottom();
		stage.addActor(tableUI);
		
		// Render Stats
		Table tableStat = new Table();
		tableStat.setFillParent(true);
		//tableStat.setDebug(true);
		
		Table tableInfo = new Table();
		//tableInfo.setDebug(true);
		tableInfo.add(new LabelMaker("HIGH SCORE", 0.5F).getLabel()).expandX().padLeft(20);
		tableInfo.add(new LabelMaker("").getLabel()).expandX().padLeft(20).padRight(20);
		tableInfo.add(new LabelMaker("LIVES", 0.5F).getLabel()).expandX().padRight(20);
		tableInfo.setColor(1F, 1F, 1F, 0.7F); // Change opacity of info
		tableInfo.row();
		
		//tableInfo.setDebug(true);
		tableInfo.add(logic.lblHighScore).width(80).expandX().padLeft(20);
		tableInfo.add(logic.lblScore).width(200).expandX().padLeft(20).padRight(20);
		tableInfo.add(logic.lblLives).width(80).expandX().padRight(20);
		tableStat.add(tableInfo).expandX().fill().padTop(10);
		
		tableStat.top();
		stage.addActor(tableStat);
	}

	public void show() {
		Gdx.input.setInputProcessor(stage);
	}
	
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        
	}
	
	public void resize(int width, int height) {
		// Updates the viewport to the screen, then centers the camera.
		viewport.update(width, height, true);
		
		Resolution.GAME_WIDTH_CURRENT = (int) viewport.getCamera().viewportWidth;
	}
	
	private void makeUi() {
		btnNewGame = new TextButton("New Game", Assets.SKIN);
		btnNewGame.getLabel().setColor(Color.WHITE);
		
	}
	
	private void addUiActions() {
		InputListener lsnNewGame = new InputListener() {
		    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		        return true;
		    }

		    public void touchUp(InputEvent evt, float x, float y, int pointer, int button) {
		    	// Make sure the cursor is still within the button.
		    	if (x > 0 && x < btnNewGame.getWidth() && y > 0 && y < btnNewGame.getHeight()) {
		    		stage.getRoot().addAction(Actions.sequence(
		    				Actions.fadeOut(1F),
		    				Actions.run(new Runnable() {
								@Override
								public void run() {
									TerrainManager.gen();
									StarManager.clear();
									
									// Randomize terrain
									Player.startNewGame();
								}
		    				}),
		    				Actions.fadeIn(1F)));
		    	}
		    }
		};
		
		btnNewGame.addListener(lsnNewGame);
	}

}
