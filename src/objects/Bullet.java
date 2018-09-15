package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;

import window.Animation;
import window.GamePlay;
import window.Handler;
import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
/**
 * Name: Chanchev Mahendran & Praven Selvakamalan
 * Date: May 23, 2107
 * Purpose: The purpose of this class is to define a fireball that the user shoots
 */
public class Bullet extends GameObject{
	Texture tex= GamePlay.getInstance();
	Handler handler;
	private Animation BulletShot;
	float initX = 0;
	
	public Bullet(float x, float y, Handler handler, ObjectId id, int vX) {
		super(x, y, id);
		this.vX=vX;
		this.handler = handler;
		initX = x;
		BulletShot= new Animation(2, tex.bullet[0]);
		
	}

	/**Purpose: updates bullet functions at 60 ticks/second
	 * Pre: n/a
	 * Pros: no return
	 */
	public void tick(LinkedList<GameObject> object) {
		x+=vX;
		BulletShot.runAnimation();
		if ((x - initX) > 800){
			handler.removeObject(this);//removes object after distance is travelled
		}
		
		Collision(object); //check collision with other objects
	}

	private void Collision(LinkedList<GameObject> object) {
		//used to check the linked list of the handler
		for (int i = 0; i < handler.object.size(); i++) { 
			//creates a temporary object
			GameObject tempObject = handler.object.get(i); 
			if ((tempObject.getId() == ObjectId.Block2) || (tempObject.getId() == ObjectId.Enemy)) {
				//bullet collides with block or enemy																														
				if (getBounds().intersects(tempObject.getBounds())) {
					//decreases the helth of the obstacle by one
					tempObject.health -= 1;
					//if there is no health left the object is removed from the game
					if (tempObject.health<=0){
						handler.removeObject(tempObject); //remove blocks on hit
						//score is based on the type of obstacle
						Player.score += tempObject.healthMax*50;
					}
					handler.removeObject(this); // remove bullet on hit
					
				}
			}
			
			
		}
	}
	/**Purpose: draws the bullets
	 * Pre: n/a
	 * Pros: no return
	 */
	public void render(Graphics pic) {
	
		pic.drawImage(tex.bullet[0],(int)x,(int)y,25,25,  null);
		
	}

	/**Purpose: collision detection box
	 * Pre: n/a
	 * Pros: returns rectangle
	 */
	public Rectangle getBounds() {
		
		return new Rectangle ((int)x, (int)y,16,16);
	}

}
