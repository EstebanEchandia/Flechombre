/**
 * 
 */
package com.team.game.objects;

import com.team.engine.GameContainer;
import com.team.engine.Renderer;
import com.team.engine.gfx.ImageTile;
import com.team.game.GameManager;
import com.team.game.components.AABBComponent;

/**
 * @author Pedro
 *
 */
public class Spikes extends GameObject{
	private ImageTile spikesSprite = new ImageTile("/Objetos/spikes.png", 8, 8);
	private int posFinalX;
	private int pisoTecho;
	private int asesino;
	private static int playerMatados;
	
	public Spikes(int fromX, int toX, int posY, boolean estaInvertido) { // toX > fromX
		this.tag = "spikes";
		this.posX = fromX*GameManager.TILE_SIZE;
		this.posFinalX = toX*GameManager.TILE_SIZE;
		this.posY = posY*GameManager.TILE_SIZE;
		this.height = GameManager.TILE_SIZE;
		this.width = GameManager.TILE_SIZE*(toX-fromX+1);
		this.dead = false;
		this.paddingRight = 0;
		this.paddingLeft = 0;
		this.paddingTop = 0;
		this.paddingBot = 0;
		this.asesino = 0;
		this.playerMatados=0;
		
		if(estaInvertido)
			pisoTecho = 1;
		else
			pisoTecho = 0;
	
		this.addComponent(new AABBComponent(this));
	}

	@Override
	public void update(GameContainer gc, GameManager gm, float dt) {
		this.updateComponents(gc, gm, dt);
	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {
		for(int i=(int) posX; i<=posFinalX; i+=GameManager.TILE_SIZE) {
			renderer.drawImageTile(spikesSprite, i, (int)posY, pisoTecho, asesino);
			this.renderComponents(gc, renderer);
		}
	}

	@Override
	public void collision(GameObject other) {
		if(other.getTag().equalsIgnoreCase("player")){
			asesino = 1;
			playerMatados += 1;
		}
	}

	public static int getPlayerMatados() {
		return playerMatados;
	}
	
	

}
