package framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import objects.Bullet;
import objects.Player;
import window.Handler;
/**
 * Name: Chanchev Mahendran & Praven Selvakamalan
 * Date: May 23, 2107
 * Purpose: The purpose of this class is to move the player and generate fireballs when the space bar pressed
 */
public class KeyInput extends KeyAdapter{

	Handler handler;
	
	public KeyInput(Handler handler){
		this.handler=handler;
	}
	/**Purpose: Works when a key is pressed
	 * Pre: KeyEvent e
	 * Pros: no return
	 */
	public void keyPressed(KeyEvent e){
		int key =e.getKeyCode();
		 
		for (int x=0;x<handler.object.size();x++){
			GameObject tempObject = handler.object.get(x);
			//find player object
			if (tempObject.getId()==ObjectId.Player){
				if(key== KeyEvent.VK_RIGHT){
					tempObject.setVX(5);
				}
				if (key==KeyEvent.VK_LEFT){
					tempObject.setVX(-5);
				}
				if (key==KeyEvent.VK_UP){
					tempObject.setVY(-5);
				}
				if (key==KeyEvent.VK_DOWN){
					tempObject.setVY(+5);
				}
				if (key==KeyEvent.VK_SPACE){
					
					
					
				}
			}
		}
	}//end of keyPressed
	
	/**Purpose: Works when a key is released
	 * Pre: KeyEvent e
	 * Pros: no return
	 */
	public void keyReleased(KeyEvent e){
		int key=e.getKeyCode();
		for (int x=0;x<handler.object.size();x++){
			GameObject tempObject = handler.object.get(x);
			//find player object
			if (tempObject.getId()==ObjectId.Player){
				if(key== KeyEvent.VK_RIGHT){
					tempObject.setVX(0);
				}
				if (key==KeyEvent.VK_LEFT){
					tempObject.setVX(0);
				}
				if (key==KeyEvent.VK_UP){
					tempObject.setVY(0);
				}
				if (key==KeyEvent.VK_DOWN){
					tempObject.setVY(0);
				}
				if (key== KeyEvent.VK_SPACE){
					handler.addObject(new Bullet(tempObject.getX()+30, tempObject.getY()+30, handler, ObjectId.Bullet, 7 ));
				}
			}
		}
	}//end of key released
	
}
