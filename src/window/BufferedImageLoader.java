package window;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Name: Praven Selvakamalan
 * Date: May 23, 2107
 * Purpose: The purpose of this class is to create a source of inputting files/images
 */
public class BufferedImageLoader {
	
	private BufferedImage image;
	/**Purpose: used to get an image from a file
	 * Pre: String path (pathway)
	 * Pros: returns BufferedImage image 
	 */
	public BufferedImage loadImage(String path){
		try{
		image=ImageIO.read(getClass().getResource(path));
		} catch(IOException e){
			e.printStackTrace();
		}
		return image;
	}
	
}
