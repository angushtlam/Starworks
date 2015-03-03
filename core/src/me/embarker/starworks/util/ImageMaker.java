package me.embarker.starworks.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ImageMaker {
	private Image img;
	
	public ImageMaker(Texture texture, int x, int y) {
		img = new Image(texture);
		img.setPosition(x, y);
	}
	
	public Image getImage() {
		return img;
	}
}