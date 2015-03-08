package me.embarker.starworks.game;

import me.embarker.starworks.render.StarManager;
import me.embarker.starworks.util.Assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class FireworkBehavior extends Actor {
	private static int IGNITION_SIZE = 141;

	private Firework fw;
	
	private float deltaCounter;
	private boolean active = true;
	private boolean fade = false;
	private boolean move = true;
	private boolean caught = false;

	public FireworkBehavior(Firework fw) {
		this.fw = fw;
		
		this.setSize(IGNITION_SIZE, IGNITION_SIZE);
		this.setPosition(
				0 - IGNITION_SIZE / 2 + Assets.FIREWORK_TRAIL.getWidth() / 2,
				0 - IGNITION_SIZE / 2 + Assets.FIREWORK_TRAIL.getHeight()); // Position the zone properly.

		this.addListener(this.getListener());
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		final Group parent = this.getParent();

		if (Player.LIVES == 0) {
			this.getTrail().setColor(Color.RED); // The trail has the index of 1
			fade = true;
		}

		if (!Player.GAME_PAUSED) {
			deltaCounter = deltaCounter + delta; // Keep track of seconds passed.	
		}
		
		// Only move if the object can move and if there is a need to move. (So it doesn't try to move every tick)
		if (move && deltaCounter > fw.getMoveSpeed()) {
			
			// Check how many steps the game needs. This is needed in case the device runs REALLY slowly.
			float step = (deltaCounter / fw.getMoveSpeed()); 
			for (int i = 0; i < step; i++) {
				deltaCounter =- fw.getMoveSpeed();
				parent.setY(parent.getY() + 1); // Move entire group up by 1.
			}

			// Check if the head of the object has left the screen.
			if (active && parent.getY() > IGNITION_SIZE) { // Point of reference for the firework trail is at the bottom.
				Player.LIVES = Player.LIVES - 1; // Remove 1 life from player.
				GameTracker.FIREWORK_SPEED_MODIFIER = Math.max(0.75F,
						GameTracker.FIREWORK_SPEED_MODIFIER - (Player.FIREWORK_SPEED_INCREASE_RATE * 2)); // Slow down speed if player missed.
				
				GameTracker.FIREWORK_SPAWN_MODIFIER = Math.max(0.75F,
						GameTracker.FIREWORK_SPAWN_MODIFIER - Player.FIREWORK_SPAWN_INCREASE_RATE); // Slow down spawn if player missed.
				
				if (Player.PLAY_SFX) {
					Assets.SOUND_MISS.play(1F);
				}
				
				GameTracker.SLOWDOWN_FIREWORK_LABEL = true; // Alert user of slowdown.
				
				active = false; // Do not let object register for points.
			}

			// Check if the object is totally offscreen and for some reason it didn't fade out. (Sanity check)
			if (parent.getY() > IGNITION_SIZE * 2) {
				// If the head of the trail is totally off screen, it is no longer active.
				this.getTrail().setColor(Color.RED); // Change the color of the firework indicating it is missed.
				fade = true;
			}
		}

		// If the firework has been caught.
		if (caught) {
			this.getTrail().setColor(Color.BLACK); // Change the color of the firework indicating it has counted for points.
			this.getParticles().setVisible(true); // Show the particles.
		}

		// Fade out the trail then remove it when needed.
		if (fade) {
			parent.addAction(Actions.sequence(
					Actions.fadeOut(0.4F),
					Actions.run(new Runnable() {
						@Override
						public void run() {
							parent.remove();
						}
					})));
		}
	}

	// Handles what happens when the head of the firework is interacted with.
	private InputListener getListener() {
		InputListener il = new InputListener() {
			@Override
			public boolean touchDown(InputEvent evt, float x, float y, int pointer, int button) {
				// If the firework is not active, do not respond..
				if (!active || Player.GAME_PAUSED) {
					return false;
				}

				Actor tgt = evt.getListenerActor().getParent();
				StarManager.genStar(tgt.getX() + 2, tgt.getY() + Assets.FIREWORK_TRAIL.getHeight()); // 2 pixel offset.
				Player.SCORE = Player.SCORE + 1;

				if (Player.PLAY_SFX) {
					StarManager.playSound();
				}
				
				// Change values.
				active = false;
				move = false;
				fade = true;
				caught = true;

				return true;
			}

			@Override
			public void touchUp(InputEvent evt, float x, float y, int pointer, int button) {
				return; // Nothing to do.
			}
		};

		return il;
	}
	
	private Actor getTrail() {
		int TRAIL_INDEX = 0; // Order of the trail being added to the Firework group.
		return this.getParent().getChildren().get(TRAIL_INDEX);
	}
	
	private Actor getParticles() {
		int PARTICLE_INDEX = 2; // Order of the particles being added to the Firework group.
		return this.getParent().getChildren().get(PARTICLE_INDEX);
	}
}
