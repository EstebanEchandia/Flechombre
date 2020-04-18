/**
 * 
 */
package com.team.game;

import java.awt.event.KeyEvent;

import com.team.engine.GameContainer;
import com.team.engine.State;
import com.team.engine.Renderer;
import com.team.engine.gfx.Image;

/**
 * @author Pedro
 *
 */
public class Levels {
	private Image levelImage = new Image("/Menu/pantallaInicio.png");
	private int mouseX, mouseY;
	
	public void update(GameContainer gc) {
		mouseX = gc.getInput().getMouseX();
		mouseY = gc.getInput().getMouseY();
		if(gc.getInput().isButtonUp(1) && (mouseX>=76 && mouseX<=220) && (mouseY>=20 && mouseY<=180)) {
			mouseX = (gc.getInput().getMouseX()-60)/16;
			mouseY = (gc.getInput().getMouseY()-20)/16;
			gc.setLevelSeleccionado(mouseX+mouseY*10);
			gc.setState(State.GAME);
		}
		
		if(gc.getInput().isKey(KeyEvent.VK_ESCAPE))
			gc.setState(State.MENU);
	}
	
	public void render(GameContainer gc, Renderer renderer) {
		renderer.drawImage(levelImage, 0, 0);
		
		renderer.drawText("LEVELS", 125, 9, 0xffff0000);
		for(int i=1; i<10; i++) {
			for(int j=0; j<10; j++) {
				renderer.drawRect(60+i*16, 20+j*16, 16, 16, 0xffff0000);
				renderer.drawText(""+(i+j*10), 60+i*16+1, 20+j*16+3, 0xffff0000);
			}
		}
	}
}
