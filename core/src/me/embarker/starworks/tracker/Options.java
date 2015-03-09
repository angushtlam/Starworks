package me.embarker.starworks.tracker;

import me.embarker.starworks.util.Assets;
import me.embarker.starworks.util.ImageMaker;
import me.embarker.starworks.util.LabelMaker;
import me.embarker.starworks.util.Resolution;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Options extends Group {
	public TextButton btnSfx;
	public TextButton btnMusic;
	public TextButton btnX;
	
	public Options() {
		this.setVisible(false);
		
		this.setSize(350, Resolution.GAME_HEIGHT);
		makeButtons();
		
		this.addActor(new ImageMaker(Assets.UI_OPTIONS, 0, 0).getImage());

		Table table = new Table();
		//table.setDebug(true);
		table.setFillParent(true);
		
		Table tableMusic = new Table();
		tableMusic.add(btnSfx).size(80, 60).padRight(5);
		tableMusic.add(btnMusic).size(80, 60).padLeft(5);
		table.add(tableMusic).expandX().fill().padBottom(30);
		
		table.row();

		table.add(new LabelMaker("Starworks", 2.0F).getLabel());
		table.row();
		table.add(new LabelMaker("© 2015 Embarker", 0.5F).getLabel());
		table.row();
		table.add(new LabelMaker(" ").getLabel());
		table.row();
		table.add(new LabelMaker("Music:").getLabel());
		table.row();
		table.add(new LabelMaker("\"Light in color\"", 0.75F).getLabel());
		table.row();
		table.add(new LabelMaker("by Moki McFly", 0.75F).getLabel());
		table.row();
		table.add(new LabelMaker("CC BY NC ND", 0.5F).getLabel());
		table.row();
		table.add(new LabelMaker(" ").getLabel());
		table.row();
		table.add(new LabelMaker("SFX:").getLabel());
		table.row();
		table.add(new LabelMaker("http://freesfx.co.uk", 0.75F).getLabel());
		
		table.center();
		this.addActor(table);
		
		Table tableX = new Table();
		tableX.setFillParent(true);
		tableX.add(btnX).size(60, 60).expand().bottom().right().pad(20);
		this.addActor(tableX);
	}

	private void makeButtons() {
		btnSfx = new TextButton("SFX", Assets.SKIN);
		InputListener lsnSfx = new InputListener() {
		    @Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		        return true;
		    }

		    @Override
			public void touchUp(InputEvent evt, float x, float y, int pointer, int button) {
		    	// Make sure the cursor is still within the button.
		    	if (x > 0 && x < btnSfx.getWidth() && y > 0 && y < btnSfx.getHeight()) {
		    		Player.PLAY_SFX = !Player.PLAY_SFX;
		    		Player.save();
		    		
		    		if (Player.PLAY_SFX) {
		    			btnSfx.setColor(Color.WHITE);
		    			btnSfx.getLabel().setColor(Color.WHITE);
		    		} else {
		    			btnSfx.setColor(Color.BLACK);
		    			btnSfx.getLabel().setColor(Color.GRAY);
		    		}
		    		
		    		if (Player.PLAY_SFX) {
						SoundEffect.playUISound();
					}
		    	}
		    }
		};
		if (!Player.PLAY_SFX) {
			btnSfx.setColor(Color.BLACK);
			btnSfx.getLabel().setColor(Color.GRAY);
		}
		btnSfx.addListener(lsnSfx);
		
		btnMusic = new TextButton("MUSIC", Assets.SKIN);
		InputListener lsnMusic = new InputListener() {
		    @Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		        return true;
		    }

		    @Override
			public void touchUp(InputEvent evt, float x, float y, int pointer, int button) {
		    	// Make sure the cursor is still within the button.
		    	if (x > 0 && x < btnMusic.getWidth() && y > 0 && y < btnMusic.getHeight()) {
		    		if (Player.PLAY_SFX) {
						SoundEffect.playUISound();
					}
		    		
		    		Player.PLAY_MUSIC = !Player.PLAY_MUSIC;
		    		Player.save();
		    		
		    		BGM.check();
		    		
		    		if (Player.PLAY_MUSIC) {
		    			btnMusic.setColor(Color.WHITE);
		    			btnMusic.getLabel().setColor(Color.WHITE);
		    		} else {
		    			btnMusic.setColor(Color.BLACK);
		    			btnMusic.getLabel().setColor(Color.GRAY);
		    		}
		    	}
		    }
		};
		if (!Player.PLAY_MUSIC) {
			btnMusic.setColor(Color.BLACK);
			btnMusic.getLabel().setColor(Color.GRAY);
		}
		btnMusic.addListener(lsnMusic);
		
		btnX = new TextButton("X", Assets.SKIN);
		InputListener lsnX = new InputListener() {
		    @Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		        return true;
		    }

		    @Override
			public void touchUp(InputEvent evt, float x, float y, int pointer, int button) {
		    	// Make sure the cursor is still within the button.
		    	if (x > 0 && x < btnX.getWidth() && y > 0 && y < btnX.getHeight()) {
		    		if (Player.PLAY_SFX) {
						SoundEffect.playUISound();
					}
		    		
		    		final Group parent = btnX.getParent().getParent();
		    		parent.addAction(Actions.sequence(
		    				Actions.scaleTo(0F, 1F, 0.15F),
		    				Actions.run(new Runnable() {
								@Override
								public void run() {
									parent.setVisible(false);
									parent.setScaleX(1F);
								}
		    				})));
		    	}
		    }
		};
		
		btnX.addListener(lsnX);
	}
}
