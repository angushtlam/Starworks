package me.embarker.starworks.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.embarker.starworks.Starworks;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.width = 432;
		cfg.height = 768;
		new LwjglApplication(new Starworks(), cfg);
	}
}
