package me.embarker.starworks.game;

import java.util.Random;

import me.embarker.starworks.util.Assets;
import me.embarker.starworks.util.ImageMaker;
import me.embarker.starworks.util.LabelMaker;
import me.embarker.starworks.util.Resolution;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class Firework extends Group {
	private static Random rand = new Random();
	
	public static float FIREWORK_INTERVAL_IN_SEC = 0.75F; // How many sec will it take for another firework to spawn.
	private static float MOVE_RATE_IN_SEC = 0.005F; // How many seconds will it take to move 1 pixel up;
	private static int PARTICLE_EXPLOSION_AREA = 80;
	private static int PARTICLE_AMT_MAX = 120;
	private static int PARTICLE_AMT_MIN = 40;
	
	private float moveSpeed;
	
	private Group groupParticles;
	
	public Firework(int x) {
		Image imgTrail = new Image(FireworkRender.getRandomTrail());
		this.addActor(imgTrail);
		
		Image imgHead = new Image(Assets.FIREWORK_HEAD);
		imgHead.setColor(Color.CYAN);
		imgHead.setPosition(
				0 - Assets.FIREWORK_HEAD.getWidth() / 2 + Assets.FIREWORK_TRAIL.getWidth() / 2,
				0 - Assets.FIREWORK_HEAD.getHeight() / 2 + Assets.FIREWORK_TRAIL.getHeight());
		this.addActor(imgHead);

		groupParticles = new Group();
		float bloomScale = (rand.nextInt(10) + 5) / 10.0F;
		Image imgBloom = new ImageMaker(Assets.FIREWORK_BLOOM,
				-10,
				-10).getImage();
		imgBloom.setColor(rand.nextFloat(), 0.5F, rand.nextFloat(), 1.0F);
		imgBloom.setOrigin(Align.center);
		imgBloom.setScale(bloomScale);
		groupParticles.addActor(imgBloom);
		
		groupParticles.setPosition(
				0 - PARTICLE_EXPLOSION_AREA / 2 + Assets.FIREWORK_TRAIL.getWidth() / 2,
				0 - PARTICLE_EXPLOSION_AREA / 2 + Assets.FIREWORK_TRAIL.getHeight());
		
		int numOfFrags = rand.nextInt(PARTICLE_AMT_MAX - PARTICLE_AMT_MIN) + PARTICLE_AMT_MIN;
		for (int i = 0; i < numOfFrags; i++) {
			Image partImg = new ImageMaker(
					Assets.GAME_STAR_FRAG, rand.nextInt(PARTICLE_EXPLOSION_AREA),
					rand.nextInt(PARTICLE_EXPLOSION_AREA)).getImage();
			partImg.setColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1.0F);
			groupParticles.addActor(partImg);
			
		}
		
		groupParticles.setVisible(false);
		this.addActor(groupParticles);
		
		if (GameTracker.FIRST_FIREWORK_LABEL) {
			this.addActor(new LabelMaker("TAP ME", 0.5F, -20, Assets.FIREWORK_TRAIL.getHeight() + 2).getLabel());
			GameTracker.FIRST_FIREWORK_LABEL = false;
		} else if (GameTracker.SPEEDUP_FIREWORK_LABEL) {
			Label lbl = new LabelMaker("SPEED UP!", 0.5F, -30, Assets.FIREWORK_TRAIL.getHeight() + 2).getLabel();
			this.addActor(lbl);
			GameTracker.SPEEDUP_FIREWORK_LABEL = false;
		}
		
		this.addActor(new FireworkBehavior(this));

		this.setX(x);
		this.setY(0 - Resolution.GAME_HEIGHT);
		
		this.moveSpeed = (MOVE_RATE_IN_SEC / GameTracker.FIREWORK_SPEED_MODIFIER);
	}

	public float getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
}
