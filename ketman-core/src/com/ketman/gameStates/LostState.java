package com.ketman.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ketman.main.ketman;
import com.ketman.managers.CoinManager;
import com.ketman.managers.FontManager;
import com.ketman.managers.GameStateManager;

public class LostState extends GameState{
	
	BitmapFont titleFont;
	SpriteBatch sb;

	public LostState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		sb = new SpriteBatch();
		// TODO Auto-generated method stub
		titleFont = FontManager.getFont(FontManager.HyperspaceBold, 150, Color.WHITE);
		
		
	}

	@Override
	public void update(float deltaTime) {
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) gsm.setState(GameStateManager.MENU);
		
		
	}

	@Override
	public void draw() {
		sb.setProjectionMatrix(ketman.cam.combined);
		sb.begin();
		
		GlyphLayout titleLayout = FontManager.getTextLayout(titleFont, "GAME OVER");
		titleFont.draw(sb, titleLayout, (ketman.width-titleLayout.width)/2, (float)(ketman.height+titleLayout.height*2)/2);
		FontManager.drawLabel("You've collected " + CoinManager.collectedCoins + " coins.", FontManager.HyperspaceBold, 30, Color.WHITE, sb, ketman.height*(float)0.4+40);
		FontManager.drawLabel("You can use ESC to return to main menue.", FontManager.HyperspaceBold, 30, Color.WHITE, sb, ketman.height*(float)0.4);
		
		sb.end();
	}


	@Override
	public void dispose() {
		sb.dispose();
		titleFont.dispose();
		
	}

}
