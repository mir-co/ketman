package com.ketman.gameStates;

import java.awt.FontMetrics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.ketman.main.ketman;
import com.ketman.managers.FontManager;
import com.ketman.managers.GameStateManager;

public class HelpState extends GameState {
	
	private SpriteBatch sb;
	private BitmapFont titleFont;
	private BitmapFont font;
	private String[] textItems;
	
	public HelpState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		sb = new SpriteBatch();

		titleFont = FontManager.getFont(FontManager.HyperspaceBold, 150, Color.WHITE);
		font = FontManager.getFont(FontManager.HyperspaceBold, 30, Color.WHITE);
		
		textItems = new String[] {
				"Thank you for playing this game.",
				"",
				"Your goal is to collect all the coins before an enemy hits you.",
				"If you managed to get all of them, you reach the next level.",
				"",
				"You can move using W/A/S/D or the arrow keys.",
				"As you reach higher levels, the game difficulty will increase.",
				"",
				"You can use ESCAPE to get back to main menu.",
				};
	}

	@Override
	public void update(float deltaTime) {
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) gsm.setState(GameStateManager.MENU);
	}

	@Override
	public void draw() {
		ScreenUtils.clear(0, 0, 0, 1);
		sb.setProjectionMatrix(ketman.cam.combined);
		sb.begin();
		
		//Titel
		GlyphLayout titleLayout = FontManager.getTextLayout(titleFont, "Help");
		titleFont.draw(sb, titleLayout, (ketman.width-titleLayout.width)/2, (float)(ketman.height*0.8));
		
		//Text
		for(int i = 0; i < textItems.length; i++) {
			
			GlyphLayout textLayout = FontManager.getTextLayout(font, textItems[i]);
			font.draw(sb, textLayout, (ketman.width-textLayout.width)/2, (float) ( (ketman.height*0.8)-50*(i+5) ) );
			
		}
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
