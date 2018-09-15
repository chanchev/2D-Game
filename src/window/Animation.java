package window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
/**
 * Name: Praven Selvakamalan
 * Date: May 23, 2107
 * Purpose: The purpose of this class is to generate animations of sprites
 */
public class Animation {

	//speed of animation
	private int speed;
	//frames for each animation
	private int frames;
	//current image
	private int index=0;
	//next image after update
	private int count=0;
	
	private BufferedImage[] images;//images 
	private BufferedImage currentImg;//current image to display
	
	public Animation(int speed, BufferedImage... args){//infinte amount of parameters containging the buffered images
		this.speed=speed;
		images= new BufferedImage[args.length];
		for (int x=0;x<args.length;x++){
			images[x]=args[x];
		}
		frames=args.length;
		
	}
	/**Purpose: to run the animation
	 * Pre: n/a
	 * Pros: n/a
	 */
	public void runAnimation(){
		index++;
		if (index>speed){
			index=0;
			nextFrame();
		}
	}
	/**Purpose: changes image creating animation effect
	 * Pre: n/a
	 * Pros: n/a
	 */
	public void nextFrame(){
		currentImg=images[count%frames];
		count++;
	}
	/**Purpose: draws the animation for the screen
	 * Pre: Graphics pic (image), int x, int y (co-ordinates) 
	 * Pros: n/a
	 */
	public void drawAnimation(Graphics pic, int x, int y){
		pic.drawImage(currentImg,x,y,null);
	}
	/**Purpose: draws the animation for the screen
	 * Pre: Graphics pic (image), int x, int y (co-ordinates) , int scaleX, int scaleY(Scales the image)
	 * Pros: n/a
	 */
	public void drawAnimation(Graphics pic, int x, int y, int scaleX, int scaleY){
		pic.drawImage(currentImg,x,y, scaleX, scaleY,null);
	}
	
	
	
}
