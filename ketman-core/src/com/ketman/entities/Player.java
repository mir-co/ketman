package com.ketman.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ketman.gameStates.PlayState;
import com.ketman.managers.GameStateManager;

public class Player extends KetmanEntity {
	
	public Player(TextureRegion texture, float x, float y, float width, float height, float rotation) {
		super(x, y, texture, width, height, rotation);
		this.level = 3;
		this.speed = 500;
		this.health = 100;
		this.id = "player";
	}
	//Richtungen (zur übersichtlichen Darstellung der Spielerrotation im Gradmaß)
	int RIGHT = 0;
	int UPRIGHT = 45;
	int UP = 90;
	int UPLEFT = 90+45;
	int LEFT = 180;
	int DOWNLEFT = 180+45;
	int DOWN = 180+90;
	int DOWNRIGHT = 180+90+45;
	
	public int speed;
	int level;
	int health;
	
	public void addLevel() {
		level++;
	}
	public int getLevel() { return level;}
	
	//Bewegung mit Änderung der Rotation je nach Richtung
	public void move(float dx, float dy) {
		if(dx > 0) {
			if(dy > 0) rotation = UPRIGHT;
			if(dy==0) rotation = RIGHT;
			if(dy<0) rotation = DOWNRIGHT;
		}
		if(dx == 0) {
			if(dy > 0) rotation = UP;
			if(dy < 0) rotation = DOWN;
		}
		if(dx < 0) {
			if(dy > 0) rotation = UPLEFT;
			if(dy == 0) rotation = LEFT;
			if(dy < 0) rotation = DOWNLEFT;
		}
		super.move(dx, dy);
	}
	
	//Wenn der Spieler außerhalb des Spielfeldes ist, wird seine Position angepasst
	public void checkBorders(int displayWidth, int displayHeight) {
		if(x < 0) x = 0;
		if(x > displayWidth-width) x = displayWidth-width;
		if(y < 0) y = 0;
		if(y > displayHeight-height) y = displayHeight-height;
	}
	public int getHealth() {
		return health;
	}
	public void removeHealth(int rem) {
		health=health-rem;
	}


}
