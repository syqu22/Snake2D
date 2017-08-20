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
	
	private static final int START_BUTTON_WIDTH = 296;
	private static final int START_BUTTON_HEIGHT = 128;
	private static final int OPTIONS_BUTTON_WIDTH = 296;
	private static final int OPTIONS_BUTTON_HEIGHT = 128;
	private static final int EXIT_BUTTON_WIDTH = 296;
	private static final int EXIT_BUTTON_HEIGHT = 128;
	private static final int START_BUTTON_Y = 400;
	private static final int OPTIONS_BUTTON_Y = 250;
	private static final int EXIT_BUTTON_Y = 100;
	
	boolean debugpos = false;
	
	SnakeGame game;
	
	OrthographicCamera camera;
	BitmapFont debug;
	Texture title,startbt,optionsbt,exitbt,startbt_select,optionsbt_select,exitbt_select;
	
	private float width = Gdx.graphics.getWidth();
	private float height = Gdx.graphics.getHeight();
	
	public MainMenu(SnakeGame game) {
		this.game = game;
		title = new Texture("menu/title.png");
		startbt = new Texture("menu/start_button.png");
		startbt_select = new Texture("menu/start_button_select.png");
		optionsbt = new Texture("menu/options_button.png");
		exitbt = new Texture("menu/exit_button.png");
		optionsbt_select = new Texture("menu/options_button_select.png");
		exitbt_select = new Texture("menu/exit_button_select.png");
		debug = new BitmapFont();
		camera = new OrthographicCamera(width,height);
		camera.position.set(camera.viewportWidth /2f, camera.viewportHeight/2f , 0);
		camera.update();
		debug.setColor(Color.WHITE);
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
	
	int x = game.WIDTH / 2 - START_BUTTON_WIDTH /2;
	if(Gdx.input.getX() < x + START_BUTTON_WIDTH & Gdx.input.getX() > x && Gdx.input.getY()+155 < START_BUTTON_Y + START_BUTTON_HEIGHT &&Gdx.input.getY() + 170> START_BUTTON_Y) {
		game.batch.draw(startbt_select,x,START_BUTTON_Y,START_BUTTON_WIDTH,START_BUTTON_HEIGHT);
		if(Gdx.input.isTouched()) {
			game.setScreen(new MainScreen(game));
		}
	}else {
		game.batch.draw(startbt,x,START_BUTTON_Y,START_BUTTON_WIDTH,START_BUTTON_HEIGHT);
	}
	if(Gdx.input.getX() < x + OPTIONS_BUTTON_WIDTH & Gdx.input.getX() > x &&Gdx.input.getY()-140 <OPTIONS_BUTTON_Y + OPTIONS_BUTTON_HEIGHT &&Gdx.input.getY() - 140> OPTIONS_BUTTON_Y) {
		game.batch.draw(optionsbt_select,x,OPTIONS_BUTTON_Y,OPTIONS_BUTTON_WIDTH,OPTIONS_BUTTON_HEIGHT);
		if(Gdx.input.isTouched()) {
		game.setScreen(new OptionsScreen(game));
		}
	}else {
		game.batch.draw(optionsbt,x,OPTIONS_BUTTON_Y,OPTIONS_BUTTON_WIDTH,OPTIONS_BUTTON_HEIGHT);
	}
	if(Gdx.input.getX() < x + EXIT_BUTTON_WIDTH & Gdx.input.getX() > x &&Gdx.input.getY()-440 < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT &&Gdx.input.getY() - 440> EXIT_BUTTON_Y) {
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
	if(Gdx.input.isKeyJustPressed(Keys.P) && debugpos == false) {
		debugpos = true;
	}else if(Gdx.input.isKeyJustPressed(Keys.P) && debugpos == true) {
		debugpos = false;
	}
	if(debugpos == true) {
		debug.draw(game.batch,Gdx.input.getX() + "," + Gdx.input.getY(),10,20);
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
