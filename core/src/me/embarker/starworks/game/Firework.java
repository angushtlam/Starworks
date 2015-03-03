package me.embarker.starworks.game;

import me.embarker.starworks.render.StarManager;
import me.embarker.starworks.util.Assets;
import me.embarker.starworks.util.Resolution;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Firework extends Group {
	public static float FIREWORK_INTERVAL_IN_SEC = 0.75F; // How many sec will it take for another firework to spawn.
	private static int IGNITION_SIZE = 136;
	private static float MOVE_RATE_IN_SEC = 0.0035F; // How many seconds will it take to move 1 pixel up;
	//private static Random rand = new Random();

	private float moveSpeed;
	
	public Firework(int x) {
		Image ignZone = new Image(Assets.FIREWORK_HEAD_1);
		ignZone.setPosition(
				0 - Assets.FIREWORK_HEAD_1.getWidth() / 2 + Assets.FIREWORK_TRAIL_1.getWidth() / 2,
				0 - Assets.FIREWORK_HEAD_1.getHeight() / 2 + Assets.FIREWORK_TRAIL_1.getHeight());
		this.addActor(ignZone);

		this.addActor(new Image(FireworkRender.getRandomTrail()));
		this.addActor(new FireworkBehavior());

		this.setX(x);
		this.setY(0 - Resolution.GAME_HEIGHT);
		
		this.moveSpeed = (MOVE_RATE_IN_SEC / Player.FIREWORK_SPEED_MODIFIER);
	}

	class FireworkBehavior extends Actor {
		private float deltaCounter;
		private boolean active = true;
		private boolean fade = false;
		private boolean move = true;
		private boolean caught = false;

		public FireworkBehavior() {
			this.setSize(IGNITION_SIZE, IGNITION_SIZE);
			this.setPosition(
					0 - IGNITION_SIZE / 2 + Assets.FIREWORK_TRAIL_1.getWidth() / 2,
					0 - IGNITION_SIZE / 2 + Assets.FIREWORK_TRAIL_1.getHeight()); // Position the zone properly.

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

			deltaCounter =+ delta; // Keep track of seconds passed.
			
			// Only move if the object can move and if there is a need to move. (So it doesn't try to move every tick)
			if (move && deltaCounter > moveSpeed) {
				
				// Check how many steps the game needs. This is needed in case the device runs REALLY slowly.
				float step = (deltaCounter / moveSpeed); 
				for (int i = 0; i < step; i++) {
					deltaCounter =- moveSpeed;
					parent.setY(parent.getY() + 1); // Move entire group up by 1.
				}

				// Check if the head of the object has left the screen.
				if (active && parent.getY() > IGNITION_SIZE) { // Point of reference for the firework trail is at the bottom.
					Player.LIVES = Player.LIVES - 1; // Remove 1 life from player.
					Player.FIREWORK_SPEED_MODIFIER = Math.max(0.75F, Player.FIREWORK_SPEED_MODIFIER - 0.75F); // Slow down speed if player missed.
					
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
				this.getTrail().setColor(Color.YELLOW); // Change the color of the firework indicating it has counted for points.
			}

			// Fade out the trail then remove it when needed.
			if (fade) {
				parent.addAction(Actions.sequence(
						Actions.fadeOut(0.5F),
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
				public boolean touchDown(InputEvent evt, float x, float y, int pointer, int button) {
					// If the firework is not active, do not respond..
					if (!active) {
						return false;
					}

					Actor tgt = evt.getListenerActor().getParent();
					StarManager.genStar(tgt.getX() + 2, tgt.getY() + Assets.FIREWORK_TRAIL_1.getHeight()); // 2 pixel offset.
					Player.SCORE = Player.SCORE + 1;

					// Change values.
					active = false;
					move = false;
					fade = true;
					caught = true;

					return true;
				}

				public void touchUp(InputEvent evt, float x, float y, int pointer, int button) {
					return; // Nothing to do.
				}
			};

			return il;
		}
		
		private Actor getTrail() {
			int TRAIL_INDEX = 1; // Order of the trail being added to the Firework group.
			return this.getParent().getChildren().get(TRAIL_INDEX);
		}
	}
}
