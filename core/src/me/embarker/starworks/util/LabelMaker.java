package me.embarker.starworks.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelMaker {
	private Label label;
	
	public LabelMaker(String str) {
		label = new Label(str, Assets.SKIN);
	}
	
	public LabelMaker(String str, float scale) {
		label = new Label(str, Assets.SKIN);
		label.setFontScale(scale);
	}
	
	public LabelMaker(String str, int x, int y) {
		label = new Label(str, Assets.SKIN);
		label.setPosition(x, y);
	}
	
	public LabelMaker(String str, boolean centered, int y) {
		label = new Label(str, Assets.SKIN);
		label.setPosition(centered ? (Resolution.GAME_WIDTH_CURRENT / 2) - label.getTextBounds().width : 0, y);
	}
	
	public LabelMaker(String str, Color color, int x, int y) {
		label = new Label(str, Assets.SKIN);
		label.setColor(color);
		label.setPosition(x, y);
	}
	
	public LabelMaker(String str, Color color, boolean centered, int y) {
		label = new Label(str, Assets.SKIN);
		label.setColor(color);
		label.setPosition(centered ? (Resolution.GAME_WIDTH_CURRENT / 2) - label.getTextBounds().width / 2 : 0, y);
	}
	
	public LabelMaker(String str, Color color, float scale, int x, int y) {
		label = new Label(str, Assets.SKIN);
		label.setColor(color);
		label.setFontScale(scale);
		label.setPosition(x, y);
	}
	
	public LabelMaker(String str, Color color, float scale, boolean centered, int y) {
		label = new Label(str, Assets.SKIN);
		label.setColor(color);
		label.setFontScale(scale);
		label.setPosition(centered ? (Resolution.GAME_WIDTH_CURRENT / 2) - ((label.getTextBounds().width * scale) / (2 * scale)): 0, y);
	}
	
	public Label getLabel() {
		return label;
	}
}