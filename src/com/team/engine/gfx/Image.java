/**
 * 
 */
package com.team.engine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Esteban
 *
 */
public class Image {
	private int width,height;
	private int[] pixel;
	
	public Image(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(Image.class.getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		width = image.getWidth();
		height = image.getHeight();
		pixel = image.getRGB(0, 0, width, height, null, 0, width);
		
		image.flush();
		
		
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[] getPixel() {
		return pixel;
	}

	public void setPixel(int[] pixel) {
		this.pixel = pixel;
	}
	
	

}
