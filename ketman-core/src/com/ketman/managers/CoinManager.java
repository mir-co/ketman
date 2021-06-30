package com.ketman.managers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ketman.entities.KetmanEntity;
import com.ketman.entities.Player;
import com.ketman.gameStates.PlayState;
import com.ketman.main.ketman;

public class CoinManager {
	int displayHeight;
	int displayWidth;
	ArrayList<KetmanEntity> coins;
	TextureRegion coinTexture;
	public static int collectedCoins;
	private PlayState playState;
	
	public CoinManager(int displayWidth, int displayHeight, TextureRegion coinTexture, PlayState playState) {
		this.displayHeight=displayHeight;
		this.displayWidth=displayWidth;
		this.coinTexture = coinTexture;
		this.playState = playState;
		coins = new ArrayList<KetmanEntity>();
		collectedCoins = 0;
	}
	public void generateCoinField() {
		for(int x = 50; x < displayWidth; x=x+150) {
			for(int y = 0; y < displayHeight; y=y+150) {
				if(Math.random() > (ketman.devMode?0.1:0.9)) continue;
				coins.add(new KetmanEntity(x, y, coinTexture));
			}
		}
	}
	public void drawCoins(SpriteBatch batch) {
		for(int i = 0; i < coins.size(); i++) {
			coins.get(i).draw(batch);
		}
	}
	public void checkCollision(Player spieler) {
		for(int i = 0; i < coins.size(); i++) {
			if(spieler.isTouching(coins.get(i))) {
				coins.remove(i);
				collectedCoins++;
				if(coins.size() == 0) {
					spieler.addLevel();
					playState.nextLevel();
					generateCoinField();
				}
			}
		}
	}
}
