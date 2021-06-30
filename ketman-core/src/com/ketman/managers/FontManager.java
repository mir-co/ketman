package com.ketman.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.ketman.main.ketman;

public class FontManager {
	
	public static final String HyperspaceBold = "Hyperspace Bold.ttf";
	
	public static BitmapFont getFont(String fontName, int size, Color color) {
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal(fontName));
		FreeTypeFontParameter fontParameter = new FreeTypeFontParameter();
		fontParameter.size = size;
		BitmapFont font = gen.generateFont(fontParameter);
		font.setColor(color);
		return font;
	}
	public static GlyphLayout getTextLayout(BitmapFont font, String text) {
		GlyphLayout textLayout = new GlyphLayout();
		textLayout.setText(font, text);
		return textLayout;
	}
	public static void drawText(String text, String font, int size, Color color, SpriteBatch sb, float posx, float posy) {
		BitmapFont f = getFont(font, size, color);
		GlyphLayout l = getTextLayout(f, text);
		f.draw(sb, l, posx, posy);
	}
	public static void drawLabel(String text, String font, int size, Color color, SpriteBatch sb, float posy) {
		BitmapFont f = getFont(font, size, color);
		GlyphLayout l = getTextLayout(f, text);
		f.draw(sb, l, (ketman.width-l.width)/2, posy);
	}
	
	

}
