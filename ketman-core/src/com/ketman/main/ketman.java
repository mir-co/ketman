package com.ketman.main;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ketman.managers.GameStateManager;

public class ketman implements ApplicationListener {
	
	public static int width;
	public static int height;
	
	public static boolean devMode = true;
	public static boolean hardMode = false;
	
	public static OrthographicCamera cam;

	private GameStateManager gsm;

	@Override
	public void create () {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		cam = new OrthographicCamera(width, height);
		cam.translate(width/2, height/2);
		cam.update();

		gsm = new GameStateManager();
		//this.setScreen(new GameScreen(this));
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
