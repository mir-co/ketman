package com.ketman.managers;

import com.badlogic.gdx.utils.ScreenUtils;
import com.ketman.gameStates.*;

public class GameStateManager {
	private GameState currentState;
	
	public static final int MENU = 0;
	public static final int PLAY = 1;
	public static final int LOST = 2;
	public static final int SETTINGS = 3;
	public static final int HELP = 4;
	
	public GameStateManager() {
		setState(MENU);
	}
	
	public void setState(int newState) {
		if(currentState != null) currentState.dispose();
		if(newState == MENU) currentState = new MenuState(this);
		if(newState == PLAY) currentState = new PlayState(this);
		if(newState == LOST) currentState = new LostState(this);
		if(newState == SETTINGS) currentState = new SettingsState(this);
		if(newState == HELP) currentState = new HelpState(this);
		
	}
	public void update(float deltaTime) {
		currentState.update(deltaTime);
	}
	public void draw() {
		/*Füllt den Bildschirm mit schwarz, damit Objekte, die in vorherigen Frames gezeichnet wurden,
		 * gelöscht werden
		 */
		ScreenUtils.clear(0, 0, 0, 1);
		
		//Ruft die draw-Methode des aktuellen GameStates auf
		currentState.draw();
	}
}
