package pl.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameOverScreen implements Screen{
	SnakeGame game;
	BitmapFont scoreFont;
	Preferences pref;
	
	private int score;
	
	public GameOverScreen(SnakeGame game) {
		this.game = game;
	}

	@Override
	public void show() {
		scoreFont = new BitmapFont();
		scoreFont.setColor(Color.YELLOW);
		
		Preferences pref = Gdx.app.getPreferences("Score");
		score = (int) pref.getFloat("score", 0);
	
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
	

		scoreFont.draw(game.batch, "Your score: " + score, 700, 500); //TODO
		
		game.batch.end();
		
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.setScreen(new MainMenu(game));
		}
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

}
