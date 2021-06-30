package com.ketman.gameStates;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ketman.main.ketman;
import com.ketman.managers.FontManager;
import com.ketman.managers.GameStateManager;

public class SettingsState extends GameState {
	
	class Setting {
		String label;
		String[] values;
		int value;
		Boolean selected;
		public Setting(String label, String[] values, int value) {
			this.label=label;
			this.values=values;
			this.value=value;
			selected=false;
		}
	}
	
	private BitmapFont titleFont;
	private BitmapFont font;
	private BitmapFont selectedFont;
	
	private SpriteBatch sb;
	private String[] textItems;
	
	private Setting[] settings;
	
	int selected;
	
	public SettingsState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		settings = new Setting[] {
				new Setting("Developer mode", new String[] {"OFF","ON"}, (ketman.devMode?1:0)),
				new Setting("Hardcore mode", new String[] {"OFF","ON"}, (ketman.hardMode?1:0))
		};
		titleFont = FontManager.getFont(FontManager.HyperspaceBold, 150, Color.WHITE);
		font = FontManager.getFont(FontManager.HyperspaceBold, 30, Color.WHITE);
		selectedFont = FontManager.getFont(FontManager.HyperspaceBold, 30, Color.RED);
		sb = new SpriteBatch();
		selected = 0;
	}

	@Override
	public void update(float deltaTime) {
		settings[selected].selected = false;
		
		//Wahl der Einstellung, die verändert werden soll
		if(Gdx.input.isKeyJustPressed(Keys.DOWN) && selected < settings.length-1) selected++;
		if(Gdx.input.isKeyJustPressed(Keys.UP) && selected > 0) selected--;
		
		settings[selected].selected = true;
		
		//Änderung der Einstellung
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT) && settings[selected].value < settings[selected].values.length-1) settings[selected].value++;
		if(Gdx.input.isKeyJustPressed(Keys.LEFT) && settings[selected].value > 0) settings[selected].value--;
		
		//Return to main menue
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			ketman.devMode = settings[0].value == 1;
			ketman.hardMode = settings[1].value == 1;
			gsm.setState(GameStateManager.MENU);
		}
		
	}

	@Override
	public void draw() {
		
		sb.setProjectionMatrix(ketman.cam.combined);
		sb.begin();
		
		//Titel
		GlyphLayout textLayout = FontManager.getTextLayout(titleFont, "Settings");
		titleFont.draw(sb, textLayout, (ketman.width-textLayout.width)/2, (float) ((ketman.height*0.8)));
		
		//Einstellungen
		for(int i = 0; i < settings.length; i++) {
			Setting current = settings[i];
			int offset = (i+3)*2;
			//Draw label
			textLayout = FontManager.getTextLayout(font, current.label);
			font.draw(sb, textLayout, (ketman.width-textLayout.width)/2, (float) ( (ketman.height*0.8)-60*(offset) ) );
			
			String valuesText = "";
			for(int x = 0; x < current.values.length; x++) {
				String c = current.values[x];
				if(current.value == x) c = "-" + c + "-";
				valuesText = (valuesText=="" ? (c) : valuesText + "/" + (c));
			}
			
			GlyphLayout valuesLayout = FontManager.getTextLayout((current.selected?selectedFont:font), valuesText);
			font.draw(sb, valuesLayout, (ketman.width-valuesLayout.width)/2, (float) (ketman.height*0.8)-60*(offset+1) );
		}
		FontManager.drawLabel("Press ESC to return to main menue.", FontManager.HyperspaceBold, 30, Color.WHITE, sb, (float) (ketman.height*0.8)-60*(11));
		
		sb.end();

	}

	@Override
	public void dispose() {
		sb.dispose();
		font.dispose();
		titleFont.dispose();
		selectedFont.dispose();
		
	}

}
