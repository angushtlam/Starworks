package me.embarker.starworks.render;

import java.util.ArrayList;
import java.util.Random;

import me.embarker.starworks.util.Assets;
import me.embarker.starworks.util.ImageMaker;
import me.embarker.starworks.util.Resolution;

import com.badlogic.gdx.scenes.scene2d.Group;

public class TerrainManager {
	private static Group group;
	private static Random rand = new Random();
	
	public static void gen(int renderAmt) {
		group.clear();
		
		ArrayList<Integer> randomRender = new ArrayList<Integer>();
		for (int i = 0; i < renderAmt; i++) {
			randomRender.add((Resolution.GAME_WIDTH_1_1 / renderAmt) * i);
		}
		
		while (!randomRender.isEmpty()) {
			int index = rand.nextInt(randomRender.size());
			
			int xOffset = rand.nextInt(20) - 20;
			group.addActor(new ImageMaker(Assets.GAME_BLD_1, randomRender.get(index) + xOffset, rand.nextInt(50) - 50).getImage());
			randomRender.remove(index);
		}
	}
	
	public static void setGroup(Group group) {
		TerrainManager.group = group;
	}
}
