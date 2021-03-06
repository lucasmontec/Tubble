package com.carvalab.tubble.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.carvalab.tubble.test.TubbleGameplayTest;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 600;

		// new LwjglApplication(new TubbleAshleyTest(), config);
		new LwjglApplication(new TubbleGameplayTest(), config);
		// new LwjglApplication(new BatchDrawingTest(), config);
	}
}
