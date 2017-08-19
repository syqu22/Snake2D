package pl.snake.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


import pl.snake.game.SnakeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Snake 2D";
		config.resizable = false;
		config.foregroundFPS = 60;
		config.addIcon("menu/icon.png", Files.FileType.Internal);
		config.width = 1280; //800 = 480
		config.height = 960; //600 = 460
		config.x = -1;
		config.y = -1;
		config.fullscreen = false;
		
		new LwjglApplication(new SnakeGame(), config);
		
		
	}
}
