package com.team.engine;

import com.team.game.Levels;
import com.team.game.Menu;

public class GameContainer implements Runnable {
	

	
	private State state = State.MENU;
	
	private boolean juegoIniciado = false; //variable de mierda mepa, arreglar

	private Thread thread;
	private Window window;
	private Renderer renderer;
	private Input input;
	private AbstractGame game;

	private boolean running = false;
	private final double UPDATE_CAP = 1.0 / 60.0;
	private int width = 320, height = 200;
	private float scale = 3f;
	private String title = "Flechombre v1.0";
	
	private int levelSeleccionado;
	
	private Menu menu;
	private Levels levels;

	public GameContainer(AbstractGame game) {
		this.game = game;
	}

	public void start() {
		window = new Window(this);
		window.getCanvas().requestFocus();
		renderer = new Renderer(this);
		input = new Input(this);
		
		menu = new Menu();
		levels = new Levels();

		thread = new Thread(this);
		thread.run(); // .run para ser la main thread// .start para q sea secundaria (side thread)
	}

	public void stop() {

	}

	public void run() {
		running = true;

		boolean render = false;
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;// lo pasamos a ms
		double passedTime = 0;
		double unprocessedTime = 0;

		double frameTime = 0;
		int frames = 0;
		int fps = 0;
		
		game.init(this);
		
		while (running) {

			render = false;

			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;

			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			while (unprocessedTime >= UPDATE_CAP) {

				unprocessedTime -= UPDATE_CAP;
				render = true;

				switch(state) {
				case GAME:
					juegoIniciado = true;
					game.update(this, (float) UPDATE_CAP);
					break;
				case MENU:
					menu.update(this);
					break;
				case LEVELS:
					levels.update(this);
					break;
				};

				input.update();
				
				if (frameTime >= 1.0) {
					frameTime = 0;
					fps = frames;
					frames = 0;
				}
			}

			if (render) {
				renderer.clear();
				
				switch(state) {
				case GAME:
					if(juegoIniciado) {
						game.render(this, renderer);
						renderer.process();
						renderer.setCamaraX(0);
						renderer.setCamaraY(0);
					}
					break;
				case MENU:
					menu.render(this, renderer);
					break;
				case LEVELS:
					levels.render(this, renderer);
					break;		
				}
			
		
				renderer.drawText("Fps: "+ fps, 0, 0, 0xffff0000);
				window.update();

				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}

		dispose();

	}

	private void dispose() {

	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Window getWindow() {
		return window;
	}

	public Input getInput() {
		return input;
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public int getLevelSeleccionado() {
		return levelSeleccionado;
	}
	public void setLevelSeleccionado(int lvl) {
		levelSeleccionado = lvl;
	}
	
}
