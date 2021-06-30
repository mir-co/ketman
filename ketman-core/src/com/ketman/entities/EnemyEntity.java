package com.ketman.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ketman.main.ketman;

public class EnemyEntity extends KetmanEntity {
	

	int MOVE_TO_DEST = 0;
	int FOLLOW_PLAYER = 1;
	int RANDOM_MOVEMENT = 2;
	
	int entityState;
	
	
	public float speed;
	int damage;
	float pathTimer;
	Vector2 destPos;
	Player target;
	
	
	public EnemyEntity(float x, float y, TextureRegion texture, float width, float height, Player target) {
		super(x, y, texture, width, height);
		speed = (float) (3+Math.random());
		damage = 30;
		pathTimer = 0;
		destPos = new Vector2(0 ,0);
		this.target=target;
		this.id = "enemyEntity";
		this.entityState = FOLLOW_PLAYER;
	}
	
	
	@Override
	public void update(float dt) {
		pathTimer=pathTimer-dt;
		
		if(entityState == FOLLOW_PLAYER) {
			destPos = target.getPosition();
			pathTimer = (float) 1.5;
		}
		if(entityState == RANDOM_MOVEMENT) {
			while(true) {
				destPos = new Vector2(ketman.width * (float)Math.random(), ketman.height * (float)Math.random());
				if(destPos.sub(this.getPosition()).len2() > 50) break;
			}
				pathTimer = (float) 2;
			}
		
		Vector2 pathVector = destPos.sub(this.getPosition()).scl(-1);
		Vector2 moveVector = pathVector.clamp(speed, speed).scl((float)-1);
		
		if(target.isTouching(this)) {
			target.removeHealth(damage);
			this.kill();
			
		}
		
		rotation = moveVector.angleDeg();
		this.move(moveVector.x, moveVector.y);
	}
	
	public void moveToDestination(float x, float y) {
		entityState = MOVE_TO_DEST;
		destPos = new Vector2(x, y);
	}
	public void followPlayer(Player target) {
		this.target  = target;
		entityState = FOLLOW_PLAYER;
	}
	public void setState(int state) {
		this.entityState = state;
	}
	public void setDamage(int dmg) {
		this.damage = dmg;
	}


	

}
