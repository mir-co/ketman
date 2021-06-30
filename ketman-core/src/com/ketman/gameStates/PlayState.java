package com.ketman.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.ketman.entities.EnemyEntity;
import com.ketman.entities.KetmanEntity;
import com.ketman.entities.Player;
import com.ketman.main.ketman;
import com.ketman.managers.CoinManager;
import com.ketman.managers.EntityManager;
import com.ketman.managers.FontManager;
import com.ketman.managers.GameStateManager;

public class PlayState extends GameState {
	
	private CoinManager coinManager;
	private EntityManager entityManager;
	Player p;
	private SpriteBatch batch;
	public BitmapFont font;
	Texture spielerImg;
	Texture coinImg;
	Texture enemyImg;
	

	public PlayState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		
		spielerImg = new Texture("ketman1.png");
		coinImg = new Texture("coinImg.png");
		enemyImg = new Texture("enemy1.png");
		coinManager = new CoinManager(ketman.width, ketman.height, new TextureRegion(coinImg), this);
		coinManager.generateCoinField();
		p = new Player(new TextureRegion(spielerImg), (ketman.width-100)/2, (ketman.height-100)/2, 100, 100, 0);
		entityManager = new EntityManager();
		addEnemy();
		entityManager.addEntity(p);
		
	}

	@Override
	public void update(float deltaTime) {
		ketman.cam.update();
		batch.setProjectionMatrix(ketman.cam.combined);
		
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) gsm.setState(GameStateManager.MENU);
		float dx = 0;
		float dy = 0;
		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) dx -= p.speed* Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) dx += p.speed * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)) dy += p.speed * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)) dy -= p.speed * Gdx.graphics.getDeltaTime();
		p.move(dx, dy);
		entityManager.updateEntities(deltaTime);
		p.checkBorders(ketman.width, ketman.height);
		coinManager.checkCollision(p);
		if(p.getHealth() <= 0) gsm.setState(GameStateManager.LOST);
		
		
	}

	@Override
	public void draw() {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.setProjectionMatrix(ketman.cam.combined);
		batch.begin();
		
		coinManager.drawCoins(batch);
		entityManager.drawEntities(batch);

		if(ketman.devMode) {
			font.draw(batch, "Health: " + p.getHealth(), 10, 90);
			font.draw(batch, "Level: " + p.getLevel(), 10, 60);
			font.draw(batch, "x: + " + p.getX() + " y: " + p.getY(), 10,30);
		}
		if(ketman.hardMode) {
			FontManager.drawLabel("HARDCORE", FontManager.HyperspaceBold, 30, Color.RED, batch, ketman.height-50);
		}
		batch.end();
		
	}
	public void nextLevel() {
		for(int i = 0; i < p.getLevel(); i++) {
			addEnemy();
		}
		for(KetmanEntity c : entityManager.getEntitiesOfType("enemyEntity")) {
			EnemyEntity cast = (EnemyEntity) c;
			cast.speed += 1;
		}
	}
	public void addEnemy() {
		EnemyEntity newEnemy = (new EnemyEntity((float)Math.random()*ketman.width, (float)Math.random()*ketman.height, new TextureRegion(enemyImg), 19*2, 10*2, p));
//		if(Math.random() > 0.5)
//			newEnemy.setState(2);
		if(ketman.hardMode) newEnemy.setDamage(100);
		while(newEnemy.isTouching(p)) {
			newEnemy.setPosition((float)Math.random()*ketman.width, (float)Math.random()*ketman.height);
		}
		entityManager.addEntity(newEnemy);
	}




	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}
