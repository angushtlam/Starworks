package me.embarker.starworks.game;

import java.util.ArrayList;
import java.util.Random;

import me.embarker.starworks.util.Assets;

import com.badlogic.gdx.graphics.Texture;

public class FireworkRender {
	private static Random rand = new Random();
	private static ArrayList<Texture> fireworkTrail = new ArrayList<Texture>();
	
	public static void init() {
		fireworkTrail.add(Assets.FIREWORK_TRAIL);
	}
	
	public static Texture getRandomTrail() {
		return fireworkTrail.get(rand.nextInt(fireworkTrail.size()));
	}
}
