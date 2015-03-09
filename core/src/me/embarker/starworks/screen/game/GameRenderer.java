package me.embarker.starworks.screen.game;

import me.embarker.starworks.util.Assets;
import me.embarker.starworks.util.ImageMaker;
import me.embarker.starworks.util.LabelMaker;
import me.embarker.starworks.util.Resolution;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameRenderer {
	private Viewport viewport;
	public Stage stage;
	
	public GameRenderer(GameLogic logic, GameUI ui) {
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
		
		// Render UI
		Table tableUI = new Table();
		tableUI.setFillParent(true);
		//table.setDebug(true);
		
		Table tableStart = new Table();
		//tableStart.setDebug(true);
		tableStart.add(ui.btnStart).expandY().bottom();
		tableUI.add(tableStart).expand().fill();
		
		tableUI.row();
		
		Table tableBtn = new Table();
		//tableBtn.setDebug(true);
		tableBtn.add(ui.btnOptions).size(60, 60).expand().bottom().left().padBottom(20).padLeft(30);
		tableBtn.add(ui.btnPause).size(60, 60).expand().bottom().right().padBottom(20).padRight(30);
		tableUI.add(tableBtn).expand().fill();
		
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
		tableInfo.add(logic.lblHighScore).width(80).expandX().padLeft(20);
		tableInfo.add(logic.lblScore).width(200).expandX().padLeft(20).padRight(20);
		tableInfo.add(logic.lblLives).width(80).expandX().padRight(20);
		tableStat.add(tableInfo).padTop(10);
		
		tableStat.row();
		
		tableStat.add(logic.lblPause).padTop(200);
		
		tableStat.top();
		
		stage.addActor(tableUI);
		stage.addActor(tableStat);
		
		stage.addActor(logic.groupOptions);
		
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

}
