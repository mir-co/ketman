package com.ketman.entities;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


public class KetmanEntity {
	public String id;
	float x;
	float y;
	TextureRegion texture;
	float width;
	float height;
	float rotation;
	boolean alive;

	public KetmanEntity(float x, float y, TextureRegion texture, float width, float height, float rotation) {
		this.x=x;
		this.y=y;
		this.texture=texture;
		this.width=width;
		this.height=height;
		this.rotation=rotation;
		this.alive = true;
	}
	public KetmanEntity(float x, float y, TextureRegion texture, float width, float height) {
		this.x=x;
		this.y=y;
		this.texture=texture;
		this.width=width;
		this.height=height;
		this.rotation = 0;
		this.alive = true;
	}
	public KetmanEntity(float x, float y, TextureRegion texture) {
		this.x=x;
		this.y=y;
		this.texture=texture;
		this.width=texture.getRegionWidth();
		this.height=texture.getRegionHeight();
		this.rotation = 0;
		this.alive = true;
	}
	
	public void setPosition(float x, float y) {
		this.x=x;
		this.y=y;
	}
	public float getX() { return x; }
	public float getY() { return y; }
	
	public void draw(SpriteBatch batch) {
		batch.draw(texture, x, y, width/2, height/2, width, height, 1, 1, rotation);
	}
	public void move(float dx, float dy) {
		x += dx;
		y += dy;
	}
	public boolean isTouching(KetmanEntity entity) {
		if(Math.abs(x + width/2 - (entity.x + entity.width/2)) < (width + entity.width)/2)
			if(Math.abs(y + height/2 - (entity.y + entity.height/2)) < (height + entity.height)/2)
				return true;
		return false;
	}
	public void kill() {
		this.alive = false;
	}
	public boolean isAlive() {
		return alive;
	}
	public void update(float dt) {
		
	}
	public Vector2 getPosition() {
		return new Vector2(x, y);
	}
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float r) {
		this.rotation = r;
	}
	
}
