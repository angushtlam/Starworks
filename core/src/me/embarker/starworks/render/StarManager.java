package me.embarker.starworks.render;

import java.util.Random;

import me.embarker.starworks.util.Assets;
import me.embarker.starworks.util.ImageMaker;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class StarManager {
	private static Group group;
	private static Random rand = new Random();
	
	// Fuck type casting.
	public static void genStar(float x, float y) {
		genStar((int) x, (int) y);
	}
	
	public static void genStar(int x, int y) {
		Image star = null;
		
		switch(1 + rand.nextInt(4)) {
			case 1:
				star = new Image(Assets.GAME_STAR_1);
				break;
			case 2:
				star = new Image(Assets.GAME_STAR_2);
				break;
			case 3:
				star = new Image(Assets.GAME_STAR_3);
				break;
			case 4:
				star = new Image(Assets.GAME_STAR_4);
				break;
		}
		
		int fragArea = 50;
		
		Group frags = new Group();
		int numOfFrags = rand.nextInt(9);
		for (int i = 0; i < numOfFrags; i++) {
			frags.addActor(new ImageMaker(Assets.GAME_STAR_FRAG, rand.nextInt(fragArea), rand.nextInt(fragArea)).getImage());
		}
		
		star.setPosition(x - star.getWidth() / 2, y - star.getHeight() / 2);
		frags.setPosition(x - fragArea / 2, y - fragArea / 2);

		group.addActor(frags);
		group.addActor(star);
	}
	
	public static void setGroup(Group group) {
		StarManager.group = group;
	}
	
	public static void clear() {
		group.clear();
	}
	
	public static void playSound() {
		switch(1 + rand.nextInt(3)) {
			case 1:
				Assets.SOUND_BREAK_1.play(0.5F, 1.5F, 1F);
				break;
			case 2:
				Assets.SOUND_BREAK_2.play(0.5F, 1.5F, 1F);
				break;
			case 3:
				Assets.SOUND_BREAK_3.play(0.5F, 1.5F, 1F);
				break;
		}
	}
}
