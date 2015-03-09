package me.embarker.starworks.screen.game;

import java.util.Random;

import me.embarker.starworks.game.Firework;
import me.embarker.starworks.render.StarManager;
import me.embarker.starworks.render.TerrainManager;
import me.embarker.starworks.tracker.GameTracker;
import me.embarker.starworks.tracker.BGM;
import me.embarker.starworks.tracker.Options;
import me.embarker.starworks.tracker.Player;
import me.embarker.starworks.util.Assets;
import me.embarker.starworks.util.Resolution;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class GameLogic {
	private GameUI ui;
	
	private float deltaCounter = 0F;
	
	private static Random rand = new Random();
	
	public Image bg;
	
	public Group groupTerrain;
	public Group groupStars;
	public Group groupFireworks;
	public Group groupUi;
	public Group groupOptions;
	
	public Label lblScore;
	public Label lblHighScore;
	public Label lblLives;
	
	public Label lblPause;
	
	public int changeRateAtScore = Player.FIREWORK_CHANGE_RATE_INCREMENT;
	
	public GameLogic(GameUI ui) {
		this.ui = ui;
		ui.setLogic(this);
		
		Player.init();
		
		bg = new Image(Assets.GAME_BG);
		bg.setColor(0.05F, 0.55F, 0.8F, 0.8F);
		
		groupTerrain = new Group();
		TerrainManager.setGroup(groupTerrain);
		TerrainManager.gen();
		
		groupStars = new Group();
		groupStars.setColor(0.75F, 0.75F, 1, 0.3F);
		StarManager.setGroup(groupStars);
		
		groupFireworks = new Group();
		
		groupOptions = new Options();
		
		lblScore = new Label("-", Assets.SKIN);
		lblScore.setFontScale(2.5F);
		lblScore.setAlignment(Align.center);
		
		lblLives = new Label("3", Assets.SKIN);
		lblLives.setAlignment(Align.center);

		lblHighScore = new Label(Player.HIGH_SCORE + "", Assets.SKIN);
		lblHighScore.setAlignment(Align.center);
		
		lblPause = new Label("PAUSED", Assets.SKIN);
		lblPause.setFontScale(4F);
		lblPause.setAlignment(Align.center);
		lblPause.setVisible(false);
		
		BGM.start();
	}
	
	public void update(float delta) {
		if (!Player.GAME_PAUSED) {
			deltaCounter = deltaCounter + delta; // Keep track of seconds passed.
		}
		
		// Save high score at end of game.
		if (!Player.HIGH_SCORE_SAVED && Player.LIVES == 0) {
			Player.HIGH_SCORE_SAVED = true;
			Player.save();
		}
		
		// Update score when needed. Set neew highscore if needed.
		if (!lblScore.getText().equals(Player.SCORE)) {
			lblScore.setText("" + Player.SCORE);
			
			if (Player.HIGH_SCORE < Player.SCORE) {
				Player.HIGH_SCORE_SAVED = false;
				Player.HIGH_SCORE = Player.SCORE;
				lblHighScore.setText("" + Player.HIGH_SCORE);
			}
		}
		
		// Update lives if lives needs to be updated.
		if (Player.LIVES >= 0 && !lblLives.getText().equals(Player.LIVES)) {
			lblLives.setText("" + Player.LIVES);
		}
		
		// If the game has ended.
		if (Player.LIVES == 0 && GameTracker.GAME_IN_PROGRESS) {
			GameTracker.SHOW_START_BUTTON = true;
			GameTracker.GAME_IN_PROGRESS = false;
			
			GameTracker.SLOWDOWN_FIREWORK_LABEL = false; // Reset slowdown label boolean to prevent it from showing up next game.
			
			// Reset change rate increment when the game is over
			if (changeRateAtScore != Player.FIREWORK_CHANGE_RATE_INCREMENT) {
				changeRateAtScore = Player.FIREWORK_CHANGE_RATE_INCREMENT;
			}
			
			if (GameTracker.SHOW_START_BUTTON) {
				GameTracker.SHOW_START_BUTTON = false;
				ui.btnStart.addAction(Actions.sequence(
						Actions.delay(2F),
	    				Actions.run(new Runnable() {
							@Override
							public void run() {
								ui.btnStart.setVisible(true);
							}
	    				}),
	    				Actions.fadeIn(3F)));
			}
		}
		
		
		// Speed up when the player reaches the next score change.
		if (Player.SCORE == changeRateAtScore) {
			GameTracker.SPEEDUP_FIREWORK_LABEL = true;
			
			GameTracker.FIREWORK_SPAWN_MODIFIER = GameTracker.FIREWORK_SPAWN_MODIFIER + Player.FIREWORK_SPAWN_INCREASE_RATE;
			GameTracker.FIREWORK_SPEED_MODIFIER = GameTracker.FIREWORK_SPEED_MODIFIER + Player.FIREWORK_SPEED_INCREASE_RATE;
			changeRateAtScore = changeRateAtScore + Player.FIREWORK_CHANGE_RATE_INCREMENT;
		}
		
		if (Player.LIVES > 0) {
			float rate = Firework.FIREWORK_INTERVAL_IN_SEC / GameTracker.FIREWORK_SPAWN_MODIFIER;
			if (deltaCounter > rate) {
				deltaCounter = 0; // Cheap workaround to prevent stacked spawning. May lead to inaccuracy in spawning
				
				int padding = ((Resolution.GAME_WIDTH_CURRENT - Resolution.GAME_WIDTH_16_9) / 2) + 15;
				groupFireworks.addActor(new Firework(rand.nextInt(Resolution.GAME_WIDTH_CURRENT - padding * 2) + padding));
				//groupFireworks.addActorAt(0, new Firework(rand.nextInt(Resolution.GAME_WIDTH_CURRENT - padding * 2) + padding));
				// Supposed to fix the order of which the firework gets tapped, didn't work as intended.
				// For some reason the trail blocks the tap zone of another firework's.
			}
		}
		
		
	}
	
}
