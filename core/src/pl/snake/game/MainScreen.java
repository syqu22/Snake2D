package pl.snake.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class MainScreen implements Screen{
	
	final float appWidth = 1280;
	final float appHeight = 720;
	
	private OrthographicCamera camera;
	private Texture snake_headtx,snake_bodytx,apple1tx,apple2tx,apple3tx,terraintx,backgroundtx;
	private Sprite apple1,apple2,apple3,terrain,background;
	private BitmapFont debug,score,size;
	private Rectangle snake_headrt,apple1rt,apple2rt,apple3rt;
	private Array<Sprite> snake_body;
	private Array<Rectangle> snake_bodyrt;
	private Array<Sound> sounds;
	private ShapeRenderer worldLine;
	//private Array<Music> music;
	private ArrayList<Float> lastX;
	private ArrayList<Float> lastY;
	private Score sc;
	private Preferences pref;
	
	SnakeGame game;

	private int frames;
	private int keyDelay;
	//private int randomSound;
	private int moveDirection;
	private int canAddBody = 0;
	private int snakeSize;
	private boolean canMove = true;
	private boolean canPlayRandomSound = false;
	private boolean debugpos = false;
	private boolean spawnApple1 = true;
	private boolean spawnApple2 = true;
	private boolean spawnApple3 = true;
	private float width = Gdx.graphics.getWidth();
	private float height = Gdx.graphics.getHeight();
	
	public MainScreen(SnakeGame game) {
		this.game = game;
	}
	
	

	@Override
	public void show() {
		
		pref = Gdx.app.getPreferences("Score");
		
		worldLine = new ShapeRenderer();
		
		camera = new OrthographicCamera(width, height);
		camera.setToOrtho(false,appWidth,appHeight);
		camera.position.set(camera.viewportWidth /2f, camera.viewportHeight/2f , 0);
		camera.update();
		
		
		Sound snakeNoise = Gdx.audio.newSound(Gdx.files.internal("sound/snake_noise.mp3"));
		//Sound snakeEating = Gdx.audio.newSound(Gdx.files.internal("sound/snake_eating.mp3"));
		sounds = new Array<Sound>();//SOUNDS
		sounds.add(snakeNoise); 	//SNAKE NOISE  - 0
		//sounds.add(snakeEating);	//SNAKE EATING - 1
		
		
		sc = new Score();
		debug = new BitmapFont();
		debug.setColor(Color.BLACK);
		score = new BitmapFont();
		score.setColor(Color.BLACK);
		size = new BitmapFont();
		size.setColor(Color.BLACK);
		
		backgroundtx = new Texture("graphic/background.PNG");
		background = new Sprite(backgroundtx);
	
		terraintx = new Texture("graphic/terrain.png");
		terrain = new Sprite(terraintx);
		
		
		snake_headtx = new Texture("graphic/snake_head.png");
		snake_bodytx = new Texture("graphic/snake_body.png");
		snake_body = new Array<Sprite>();
		snake_bodyrt = new Array<Rectangle>();
		snake_bodyrt.add(new Rectangle());	
		snake_bodyrt.add(new Rectangle());	
		
		lastX = new ArrayList<Float>();
		lastX.add((float) 100);
		lastY = new ArrayList<Float>();
		lastY.add((float) 100);
	
		apple1tx = new Texture("graphic/apple.png");
		apple2tx = new Texture("graphic/apple2.png");
		apple3tx = new Texture("graphic/apple3.png");
		
		apple1 = new Sprite(apple1tx);
		apple2 = new Sprite(apple2tx);
		apple3 = new Sprite(apple3tx);
		
		snake_headrt = new Rectangle();
		snake_headrt.width = 32;
		snake_headrt.height = 32;
		
		apple1rt = new Rectangle();
		apple1rt.width = 16;
		apple1rt.height = 16;
	
		apple2rt = new Rectangle();
		apple2rt.width = 16;
		apple2rt.height = 16;
		
		apple3rt = new Rectangle();
		apple3rt.width = 16;
		apple3rt.height = 16;
		
		snake_body.add(new Sprite(snake_headtx));//HEAD
		snake_body.add(new Sprite(snake_bodytx)); //FIRST BODY
		snake_body.add(new Sprite(snake_bodytx));
		snake_body.add(new Sprite(snake_bodytx));
		
		
	}

	@Override
	public void render(float delta) {
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		worldLine.setProjectionMatrix(camera.combined);
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		game.batch.begin(); //BEGIN
	
		//randomSound();
		placeTerrain();
		spawnApples();
		drawScore();
		drawBody();
		moveHead();
		moveBody();		
		bodyRectangle();	
		rectanglesPosition();	
		isPressed();	
		collisionWithApples();
		collisionWithEdges();
		collisionWithBody();	
		zoomCamera();
		
		frameTimer();	
		debugPos();
		snakeSize = snake_body.size;
		drawWorldLines();
		
		game.batch.end(); //END
		
	}
	
	public void bodyRectangle() {
		
		for(int i=1;i<snake_body.size;i++) {
			snake_bodyrt.add(new Rectangle());			
		}
		
		
		for(int i=3;i<snake_body.size;i++) {
			snake_bodyrt.get(i).setX(snake_body.get(i).getX());
			snake_bodyrt.get(i).setY(snake_body.get(i).getY());
			snake_bodyrt.get(i).setWidth(1);
			snake_bodyrt.get(i).setHeight(1);
		}
		
		
	}
	
	public void rectanglesPosition() {
		snake_headrt.x = snake_body.get(0).getX();
		snake_headrt.y = snake_body.get(0).getY();
	
		apple1rt.x = apple1.getX();
		apple1rt.y = apple1.getY();
		
		apple2rt.x = apple2.getX();
		apple2rt.y = apple2.getY();
		
		apple3rt.x = apple3.getX();
		apple3rt.y = apple3.getY();

		
		
	}
	
	public void spawnApples() {
		Random rnd = new Random();
		int rndx = rnd.nextInt(1330);
		int rndy = rnd.nextInt(730);
		
		
		if(spawnApple1 == true) {
		spawnApple1 = false;
		rndx = rnd.nextInt(1250);
		rndy = rnd.nextInt(730);
		apple1.setPosition(rndx, rndy);
			
		}
		if(spawnApple2 == true) {
		spawnApple2 = false;
		rndx = rnd.nextInt(1250);
		rndy = rnd.nextInt(730);
		apple2.setPosition(rndx, rndy);
		
		}
		if(spawnApple3 == true) {
		spawnApple3 = false;
		rndx = rnd.nextInt(1250);
		rndy = rnd.nextInt(730);
		apple3.setPosition(rndx, rndy);
		
		}
		
		apple1.draw(game.batch);
		apple2.draw(game.batch);
		apple3.draw(game.batch);
		
		
	}
	
	public void collisionWithEdges() {
		if(snake_body.get(0).getX() <= -1 || snake_body.get(0).getX() >= 1250) {
			gameOver();
		}
		if(snake_body.get(0).getY() <= -1 || snake_body.get(0).getY() >= 730) {
			gameOver();		
		}
	}
	
	public void collisionWithBody() {
		for(int i=1;i<snake_body.size;i++) {
			if(snake_bodyrt.get(i).overlaps(snake_headrt)) {	
				gameOver();
			}
		}
	}
	
	public void collisionWithApples() {
		if(canAddBody>=3) {
			canAddBody = 2;
		}
		if(snake_headrt.overlaps(apple1rt)) {
			sc.setSc(sc.getSc()+5);
			spawnApple1 = true;
			canAddBody++;
			
		}
		if(snake_headrt.overlaps(apple2rt)) {
			sc.setSc(sc.getSc()+5);
			spawnApple2 = true;
			canAddBody++;
		}
		if(snake_headrt.overlaps(apple3rt)) {
			sc.setSc(sc.getSc()+5);
			spawnApple3 = true;
			canAddBody++;
		}
	}

	public void newBody() {

			if(canAddBody == 2) {
				snake_body.add(new Sprite(snake_bodytx));			
				canAddBody = 0;
			}else if(canAddBody == 1){
				snakeSize = (int) (snakeSize + 0.5);
			}
	}
	
	public void drawBody() {
		
		for (int i = 0; i < snake_body.size; i++) {
			snake_body.get(i).draw(game.batch);
		}
		
		newBody();
	}
	
	public void debugPos() {
		if(Gdx.input.isKeyJustPressed(Keys.P) && debugpos == false) {
			debugpos = true;
		}else if(Gdx.input.isKeyJustPressed(Keys.P) && debugpos == true){
			debugpos = false;
		}if(debugpos == true) {
			size.draw(game.batch, "Size: " + snakeSize, snake_body.get(0).getX()-10, snake_body.get(0).getY() + 60);
			debug.draw(game.batch,"X: " + snake_body.get(0).getX() + "Y: " + snake_body.get(0).getY(), snake_body.get(0).getX() - 25, snake_body.get(0).getY() + 80);		
		}
	}
	
	public void isPressed() {
		keyDelay++;
		if(keyDelay>=5) {
			canMove=true;
			keyDelay=0;
		}
		if(canMove == true) {
		
		if(Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.W)) {
			if(moveDirection == 1) {
			}else {		
			moveDirection = 0;
			canMove = false;
			keyDelay = 0;		
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)|| Gdx.input.isKeyJustPressed(Keys.S)){
			if(moveDirection == 0) {		 
			}else {
			moveDirection = 1;	
			canMove = false;
			keyDelay = 0;
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.LEFT)|| Gdx.input.isKeyJustPressed(Keys.A)) {
			if(moveDirection == 3) {				
			}else {
			moveDirection = 2;	
			canMove = false;
			keyDelay = 0;
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT) || Gdx.input.isKeyJustPressed(Keys.D)) {
			if(moveDirection == 2) {			
			}else {
			moveDirection = 3;
			canMove = false;
			keyDelay = 0;
			}
		}
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			game.setScreen(new MainMenu(game));
		}
		}
		
	}
	
	public void drawScore() {
		score.draw(game.batch, "Score: " + sc.getSc(), appWidth / 2 - 20, 720);
	}
	
	public void moveHead() {
		
		if(moveDirection == 0) {
			snake_body.get(0).setPosition(snake_body.get(0).getX(),snake_body.get(0).getY() + 5 );
			snake_body.get(0).setRotation(0);
		}
		if(moveDirection == 1) {
			snake_body.get(0).setPosition(snake_body.get(0).getX(),snake_body.get(0).getY() - 5 );
			snake_body.get(0).setRotation(180);
		}
		if(moveDirection == 2) {
			snake_body.get(0).setPosition(snake_body.get(0).getX() - 5,snake_body.get(0).getY());
			snake_body.get(0).setRotation(90);
		}
		if(moveDirection == 3) {
			snake_body.get(0).setPosition(snake_body.get(0).getX() + 5,snake_body.get(0).getY());
			snake_body.get(0).setRotation(-90);
		}
	}
	
	public void moveBody() {
	
	for(int i=1;i<snake_body.size && i<lastX.size();i++) {		
			snake_body.get(i).setPosition(lastX.get(lastX.size()-i), lastY.get(lastY.size()-i));
		}	
	}
	
	public void randomSound() {
		if(canPlayRandomSound == true) {
			sounds.get(0).play(0.1f);
		}
	}

	public void zoomCamera() {
	
		if(Gdx.input.isKeyPressed(Keys.Z)) {
			camera.zoom -= 0.05f;
			
		}
		if(Gdx.input.isKeyPressed(Keys.X)) {
			camera.zoom += 0.05f;	
		}
		
		if(camera.zoom >= 2f) {
			camera.zoom = 2f;
		}
		if(camera.zoom <= 1f) {
			camera.zoom = 1f;
		}
		
	
	}
	
	public void frameTimer() {
		frames+=1;
		if(frames>=6) {		
				lastX.add(snake_body.get(0).getX());
				lastY.add(snake_body.get(0).getY());
			frames=0;	
		}
	}
	
	public void drawWorldLines() {
			score.draw(game.batch, "lol", 50, 50);
			worldLine.begin(ShapeType.Line);
			worldLine.line(0, 0, 1282, 0);
			worldLine.line(0, 0, 0,762);
			worldLine.line(1282, 762 , 1282, 0);
			worldLine.line(1282, 762, 0, 762);
			worldLine.setColor(Color.BLACK);
			worldLine.end();
	}
	
	public void placeTerrain() {
		background.setPosition(-644,-359);
		background.draw(game.batch);
		terrain.setPosition(0,0);
		terrain.draw(game.batch);
	}
	
	public void gameOver() {
		pref.putFloat("score", sc.getSc());
		game.setScreen(new GameOver(game));
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
		snake_headtx.dispose();
		apple1tx.dispose();
		apple2tx.dispose();
		apple3tx.dispose();
		terraintx.dispose();
		debug.dispose();
		score.dispose();
		size.dispose();
		worldLine.dispose();
		
	}
	
}