package com.ketman.managers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ketman.entities.KetmanEntity;

public class EntityManager {
	private ArrayList<KetmanEntity> entities;
	
	public EntityManager() {
		entities = new ArrayList<KetmanEntity>();
	}
	
	public void addEntity(KetmanEntity ent) {
		entities.add(ent);
	}
	public void updateEntities(float dt) {
		for(int i = 0; i < entities.size(); i++) {
			KetmanEntity e = entities.get(i);
			if(!e.isAlive()) {
				entities.remove(i);
				continue;
			}
			e.update(dt);
			
		}
	}
	
	public void drawEntities(SpriteBatch sb) {
		for(KetmanEntity e: entities) {
			e.draw(sb);
		}
	}
	
	public ArrayList<KetmanEntity> getEntitiesOfType(String id) {
		ArrayList<KetmanEntity> out = new ArrayList<KetmanEntity>();
		for(KetmanEntity current : entities) {
			if(current.id == id) out.add(current);
		}
		return out;
	}
}
