/**
 * 
 */
package com.team.game.objects;

import com.team.engine.GameContainer;
import com.team.engine.Renderer;
import com.team.engine.State;
import com.team.engine.gfx.ImageTile;
import com.team.game.GameManager;
import com.team.game.components.AABBComponent;

/**
 * @author Esteban
 *
 */
public class Diana extends GameObject{
	private ImageTile checkpoint;
	
	private float animacion;
	private boolean animar;
	
	public Diana(int x, int y, int facing) {
		this.tag = "diana";
		this.posX = x*GameManager.TILE_SIZE;
		this.posY = y*GameManager.TILE_SIZE;
		this.height = 32;
		this.width = 32;
		this.paddingRight = 6;
		this.paddingLeft = 8;
		this.paddingBot = 0;
		this.paddingTop = 7;
		animacion = 0;
		animar = false;
		
		switch(facing) {
		case 0:
			this.checkpoint = new ImageTile("/dianaFacingLeft.png", 32, 32);
			break;
		case 1:
			this.checkpoint = new ImageTile("/dianaFacingRight.png", 32, 32);
			break;
	}
		
		this.addComponent(new AABBComponent(this));
	}
	

	@Override
	public void update(GameContainer gc, GameManager gm, float dt) {
		if(animacion>3) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gc.setState(State.LEVELS);
		}
			
		if(animar && animacion<3) {
			animacion += dt*5;
		}
		
		this.updateComponents(gc, gm, dt);
	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {
		renderer.drawImageTile(checkpoint, (int)posX, (int)posY, (int)animacion, 0);
		this.renderComponents(gc, renderer);
	}

	@Override
	public void collision(GameObject other) {
		if(other.getTag().equalsIgnoreCase("player")) {
			animar = true;
		}
		
	
	}
}
