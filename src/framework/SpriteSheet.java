package framework;

import java.awt.image.BufferedImage;

/**
 * Name: Praven Selvakamalan
 * Date: May 23, 2107
 * Purpose: The purpose of this class is to break down sprite sheets and obtain the correct sprite
 */
public class SpriteSheet {
	
	private BufferedImage image;
	
	//constructor 
	// sprite sheet is the parameter
	public SpriteSheet (BufferedImage image){
		this.image = image;
		
	}
	//used to obtain the image from the sprite sheet
	public BufferedImage grabImage(int col, int row, int width, int height){
		
		BufferedImage img= image.getSubimage((col*width)-width,(row*height)-height, width, height);
		return img;
	}
}
