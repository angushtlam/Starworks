package me.embarker.starworks.screen.game;

import me.embarker.starworks.render.StarManager;
import me.embarker.starworks.render.TerrainManager;
import me.embarker.starworks.tracker.GameTracker;
import me.embarker.starworks.tracker.Player;
import me.embarker.starworks.tracker.SoundEffect;
import me.embarker.starworks.util.Assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GameUI {
	private GameLogic logic;
	
	public ImageButton btnStart;
	public TextButton btnOptions;
	public TextButton btnPause;
	
	public GameUI() {
		btnStart = new ImageButton(new TextureRegionDrawable(new TextureRegion(Assets.UI_START)));
		InputListener lsnNewGame = new InputListener() {
		    @Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		        return true;
		    }

		    @Override
			public void touchUp(InputEvent evt, float x, float y, int pointer, int button) {
		    	// Make sure the cursor is still within the button.
		    	if (x > 0 && x < btnStart.getWidth() && y > 0 && y < btnStart.getHeight()) {
		    		if (Player.PLAY_SFX) {
						SoundEffect.playUISound();
					}
		    		
		    		btnStart.getParent().getParent().getParent().addAction(Actions.sequence(
		    				Actions.fadeOut(0.5F),
		    				Actions.run(new Runnable() {
								@Override
								public void run() {
									TerrainManager.gen();
									StarManager.clear();
									GameTracker.startNewGame();
									Player.resetScore();
									
									logic.groupOptions.setVisible(false);
								}
		    				}),
		    				Actions.fadeIn(1.5F)));

		    		final Actor a = evt.getListenerActor();
		    		a.addAction(Actions.sequence(
		    				Actions.fadeOut(0.5F),
		    				Actions.run(new Runnable() {
								@Override
								public void run() {
									a.setVisible(false);
								}
		    				})));
		    	}
		    }
		};
		btnStart.addListener(lsnNewGame);
		
		btnOptions = new TextButton("Options", Assets.SKIN);
		InputListener lsnSfx = new InputListener() {
		    @Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		        return true;
		    }

		    @Override
			public void touchUp(InputEvent evt, float x, float y, int pointer, int button) {
		    	// Make sure the cursor is still within the button.
		    	if (x > 0 && x < btnOptions.getWidth() && y > 0 && y < btnStart.getHeight()) {
		    		// Do not allow button to be pressed when the game is in progress.
		    		if (GameTracker.GAME_IN_PROGRESS && !Player.GAME_PAUSED) {
		    			return;
		    		}
		    		
		    		if (Player.PLAY_SFX) {
						SoundEffect.playUISound();
					}
		    		
		    		logic.groupOptions.setVisible(true);
		    	}
		    }
		};
		btnOptions.getLabel().setFontScale(0.6F);
		btnOptions.addListener(lsnSfx);
		
		btnPause = new TextButton("ll", Assets.SKIN);
		InputListener lsnPause = new InputListener() {
		    @Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		        return true;
		    }

		    @Override
			public void touchUp(InputEvent evt, float x, float y, int pointer, int button) {
		    	// Make sure the cursor is still within the button.
		    	if (x > 0 && x < btnPause.getWidth() && y > 0 && y < btnPause.getHeight()) {
		    		if (!GameTracker.GAME_IN_PROGRESS) {
		    			return;
		    		}
		    		
		    		if (Player.PLAY_SFX) {
						SoundEffect.playUISound();
					}
		    		
		    		Player.GAME_PAUSED = !Player.GAME_PAUSED;
		    		
		    		if (!Player.GAME_PAUSED) {
		    			btnPause.setColor(Color.WHITE);
		    			btnPause.getLabel().setColor(Color.WHITE);
		    			logic.lblPause.setVisible(false);
		    			
						logic.groupOptions.setVisible(false);
		    		} else {
		    			btnPause.setColor(Color.RED);
		    			btnPause.getLabel().setColor(Color.BLACK);
		    			logic.lblPause.setVisible(true);
		    		}
		    	}
		    }
		};
		if (Player.GAME_PAUSED) {
			btnPause.setColor(Color.RED);
			btnPause.getLabel().setColor(Color.BLACK);
			logic.lblPause.setVisible(true);
		}
		btnPause.getLabel().setFontScale(2.0F, 1.0F);
		btnPause.addListener(lsnPause);
	}
	
	public void setLogic(GameLogic logic) {
		this.logic = logic;
	}
}
