package pl.snake.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeGame extends Game {
	public final int WIDTH = 1280;
	public final int HEIGHT = 720;
	
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenu(this));
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	

}
