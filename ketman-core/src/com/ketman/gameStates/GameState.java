package com.ketman.gameStates;


import com.ketman.managers.GameStateManager;

public abstract class GameState {
	protected GameStateManager gsm;
	
	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	public abstract void init();
	public abstract void update(float deltaTime);
	public abstract void draw();
	public abstract void dispose();
}
