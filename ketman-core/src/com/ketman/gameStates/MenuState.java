package com.ketman.gameStates;

import com.ketman.managers.GameStateManager;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ketman.main.ketman;

public class MenuState extends GameState {
	
	private SpriteBatch sb;
	
	private BitmapFont titleFont;
	private BitmapFont font;
	
	private final String title = "Ketman";
	
	private int currentItem;
	private String[] menuItems;
	
	float time;
	

	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		sb = new SpriteBatch();
		
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("Hyperspace Bold.ttf"));
		
		FreeTypeFontParameter titleFontParameter = new FreeTypeFontParameter();
		titleFontParameter.size = 150;
		titleFont = gen.generateFont(titleFontParameter);
		titleFont.setColor(Color.WHITE);
		
		FreeTypeFontParameter fontParameter = new FreeTypeFontParameter();
		fontParameter.size = 30;
		font = gen.generateFont(fontParameter);
		font.setColor(Color.WHITE);
		
		
		
		
		
		
		
		menuItems = new String[] {
				"Play",
				"Settings",
				"Help",
				"Quit"
		};
		
		
	}

	@Override
	public void update(float deltaTime) {
		time = time + deltaTime;
		if(time < 0.5) return;
		//Handle user input
		if(Gdx.input.isKeyJustPressed(Keys.UP)) {
			if(currentItem > 0) {
				currentItem--;
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			if(currentItem < menuItems.length-1) {
				currentItem++;
			}
			
		}
		if(Gdx.input.isKeyPressed(Keys.ENTER)) {
			if(currentItem == 0) {
				gsm.setState(GameStateManager.PLAY);
			}
			else if(currentItem == 1) {
				gsm.setState(GameStateManager.SETTINGS);
			}
			else if(currentItem == 2) {
				gsm.setState(GameStateManager.HELP);
			}
			else if(currentItem == 3) {
				Gdx.app.exit();
			}
		}
		
	}

	@Override
	public void draw() {
		sb.setProjectionMatrix(ketman.cam.combined);
		sb.begin();
		
		//Titel
		GlyphLayout titleLayout = new GlyphLayout();
		titleLayout.setText(titleFont, title);
		//titleFont.draw(sb, title, ketman.width/2, ketman.height/2);
		titleFont.draw(sb, titleLayout, (ketman.width-titleLayout.width)/2, (float)(ketman.height*0.8));
		//Menu
		for(int i = 0; i < menuItems.length; i++) {
			if(currentItem == i) font.setColor(Color.RED);
			else font.setColor(Color.WHITE);
			GlyphLayout textLayout = new GlyphLayout();
			textLayout.setText(font, menuItems[i]);
			
			font.draw(sb, textLayout, (ketman.width-textLayout.width)/2, (float) ( (ketman.height*0.8)-50*(i+5) ) );
			
			//font.draw(sb, menuItems[i], ketman.width/2, 180-35*i);
			
		}
		sb.end();
		
	}



	@Override
	public void dispose() {
		sb.dispose();
		titleFont.dispose();
		font.dispose();
		
	}

}
