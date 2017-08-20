package pl.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MainMenu implements Screen{
	
	private final float appWidth = 1280;
	private final float appHeight = 720;
	
	private static final int START_BUTTON_WIDTH = 220;
	private static final int START_BUTTON_HEIGHT = 100;
	private static final int OPTIONS_BUTTON_WIDTH = 220;
	private static final int OPTIONS_BUTTON_HEIGHT = 100;
	private static final int HIGHSCORES_BUTTON_WIDTH = 220;
	private static final int HIGHSCORES_BUTTON_HEIGHT = 100;
	private static final int EXIT_BUTTON_WIDTH = 220;
	private static final int EXIT_BUTTON_HEIGHT = 100;
	private static final int START_BUTTON_Y = 500;
	private static final int OPTIONS_BUTTON_Y = 390;
	private static final int HIGHSCORES_BUTTON_Y = 280;
	private static final int EXIT_BUTTON_Y = 170;
	SnakeGame game;
	
	OrthographicCamera camera;
	BitmapFont debug;
	Texture title,startbt,optionsbt,highscoresbt,exitbt,startbt_select,optionsbt_select,highscoresbt_select,exitbt_select;
	
	private float width = Gdx.graphics.getWidth();
	private float height = Gdx.graphics.getHeight();
	
	public MainMenu(SnakeGame game) {
		this.game = game;
		title = new Texture("menu/title.png");
		startbt = new Texture("menu/start_button.png");
		startbt_select = new Texture("menu/start_button_select.png");
		optionsbt = new Texture("menu/options_button.png");
		highscoresbt = new Texture("menu/highscores_button.png");
		exitbt = new Texture("menu/exit_button.png");
		optionsbt_select = new Texture("menu/options_button_select.png");
		highscoresbt_select = new Texture("menu/highscores_button_select.png");
		exitbt_select = new Texture("menu/exit_button_select.png");
		camera = new OrthographicCamera(width,height);
		camera.position.set(camera.viewportWidth /2f, camera.viewportHeight/2f , 0);
		camera.setToOrtho(false,appWidth,appHeight);
		camera.update();
	}

	@Override
	public void show() {
		
		
	}

	@Override
	public void render(float delta) {
	camera.update();
	game.batch.setProjectionMatrix(camera.combined);
	Gdx.gl.glClearColor(1, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	game.batch.begin();
	game.batch.draw(title,game.HEIGHT/2,600);
	//1280x960
	
	int x = game.WIDTH / 4 - START_BUTTON_WIDTH /2;
	if(Gdx.input.getX() < x + START_BUTTON_WIDTH & Gdx.input.getX() > x && Gdx.input.getY()+380 < START_BUTTON_Y + START_BUTTON_HEIGHT &&Gdx.input.getY() + 380> START_BUTTON_Y) {
		game.batch.draw(startbt_select,x,START_BUTTON_Y ,START_BUTTON_WIDTH,START_BUTTON_HEIGHT);
		if(Gdx.input.isTouched()) {
			game.setScreen(new MainScreen(game));
		}
	}else {
		game.batch.draw(startbt,x,START_BUTTON_Y,START_BUTTON_WIDTH,START_BUTTON_HEIGHT);
	}
	if(Gdx.input.getX() < x + OPTIONS_BUTTON_WIDTH & Gdx.input.getX() > x &&Gdx.input.getY() + 160 <OPTIONS_BUTTON_Y + OPTIONS_BUTTON_HEIGHT &&Gdx.input.getY() + 160> OPTIONS_BUTTON_Y) {
		game.batch.draw(optionsbt_select,x,OPTIONS_BUTTON_Y,OPTIONS_BUTTON_WIDTH,OPTIONS_BUTTON_HEIGHT);
		if(Gdx.input.isTouched()) {
		game.setScreen(new OptionsScreen(game));
		}
	}else {
		game.batch.draw(optionsbt,x,OPTIONS_BUTTON_Y,OPTIONS_BUTTON_WIDTH,OPTIONS_BUTTON_HEIGHT);
	}
	if(Gdx.input.getX() < x + HIGHSCORES_BUTTON_WIDTH & Gdx.input.getX() > x && Gdx.input.getY() - 60 < HIGHSCORES_BUTTON_Y + HIGHSCORES_BUTTON_HEIGHT && Gdx.input.getY() - 60 > HIGHSCORES_BUTTON_Y) {
		game.batch.draw(highscoresbt_select, x, HIGHSCORES_BUTTON_Y,HIGHSCORES_BUTTON_WIDTH,HIGHSCORES_BUTTON_HEIGHT);
		if(Gdx.input.isTouched()) {
			//TODO
		}
	}else {
		game.batch.draw(highscoresbt, x, HIGHSCORES_BUTTON_Y,HIGHSCORES_BUTTON_WIDTH,HIGHSCORES_BUTTON_HEIGHT);
	}
	if(Gdx.input.getX() < x + EXIT_BUTTON_WIDTH & Gdx.input.getX() > x &&Gdx.input.getY() - 280 < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT &&Gdx.input.getY() - 280> EXIT_BUTTON_Y) {
		game.batch.draw(exitbt_select,x,EXIT_BUTTON_Y,EXIT_BUTTON_WIDTH,EXIT_BUTTON_HEIGHT);
		if(Gdx.input.isTouched()){
			Gdx.app.exit();
		}
	}else {
		game.batch.draw(exitbt,x,EXIT_BUTTON_Y,EXIT_BUTTON_WIDTH,EXIT_BUTTON_HEIGHT);
	}
	if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
		Gdx.app.exit();
	}
	game.batch.end();
	
		
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
		title.dispose();
		startbt.dispose();
		startbt_select.dispose();
		optionsbt.dispose();
		optionsbt_select.dispose();
		exitbt.dispose();
		exitbt_select.dispose();
		
		
	}
	

}
